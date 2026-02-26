package Practice;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import java.util.stream.Collectors;

class InvalidTrackException extends Exception {
    public InvalidTrackException(String message) {
        super(message);
    }
}

class NoEligibleTrackException extends Exception {
    public NoEligibleTrackException(String message) {
        super(message);
    }
}
class Track {
    private String trackId;
    private String artist;
    private String title;
    private String genre;
    private double earnings;
    private int releaseYear;
    private double rating;
    private List<String> tags;

    public Track() {
        this.tags = new ArrayList<>();
    }

    public Track(String trackId, String artist, String title, String genre,
                 double earnings, int releaseYear, double rating, List<String> tags) {
        this.trackId = trackId;
        this.artist = artist;
        this.title = title;
        this.genre = genre;
        this.earnings = earnings;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.tags = tags != null ? tags : new ArrayList<>();
    }

    public String getTrackId() { return trackId; }
    public void setTrackId(String trackId) { this.trackId = trackId; }

    public String getArtist() { return artist; }
    public void setArtist(String artist) { this.artist = artist; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public double getEarnings() { return earnings; }
    public void setEarnings(double earnings) { this.earnings = earnings; }

    public int getReleaseYear() { return releaseYear; }
    public void setReleaseYear(int releaseYear) { this.releaseYear = releaseYear; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }

    @Override
    public String toString() {
        return "trackId=" + trackId +
                " | artist=" + artist +
                " | title=" + title +
                " | genre=" + genre +
                " | earnings=" + earnings +
                " | releaseYear=" + releaseYear +
                " | rating=" + rating +
                " | tags=" + tags;
    }
}

/* ===================== UTILITIES ===================== */

class TrackUtil {

    public static Track parseTrack(String input) throws InvalidTrackException {
        try {
            String[] parts = input.split(":");
            if (parts.length < 7)
                throw new InvalidTrackException("Input '" + input + "' is not in the required format.");

            String trackId = parts[0];
            String artist = parts[1];
            String title = parts[2];
            String genre = parts[3];
            double earnings = Double.parseDouble(parts[4]);
            int releaseYear = Integer.parseInt(parts[5]);
            double rating = Double.parseDouble(parts[6]);

            List<String> tags = new ArrayList<>();
            if (parts.length == 8) {
                tags = Arrays.stream(parts[7].split(","))
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .collect(Collectors.toList());
            }

            return new Track(trackId, artist, title, genre, earnings, releaseYear, rating, tags);

        } catch (NumberFormatException e) {
            throw new InvalidTrackException("Numeric parsing error in input: " + input);
        }
    }

    public static Predicate<Track> isReleasedBefore(int year) {
        return t -> t.getReleaseYear() < year;
    }

    public static Function<String, Double> genreAdjustment() {
        return genre -> {
            switch (genre.toUpperCase()) {
                case "CLASSICAL": return 1.5;
                case "ROCK": return 0.5;
                case "POP": return 0.0;
                case "ELECTRONIC": return -0.5;
                case "INDIE": return 0.75;
                default: return 0.25;
            }
        };
    }

    public static BiFunction<Track, Double, Double> computeAdjustedEarnings(int filterYear) {
        return (track, baseCommission) -> {
            double genreAdj = genreAdjustment().apply(track.getGenre());
            double ageAdj = (filterYear - track.getReleaseYear()) >= 5 ? 1.0 : 0.0;

            double commission = baseCommission + genreAdj + ageAdj;
            return track.getEarnings() - (track.getEarnings() * commission / 100.0);
        };
    }

    public static Consumer<Track> applyAdjustedEarnings(double adjusted) {
        return t -> t.setEarnings(adjusted);
    }

    public static Comparator<Track> byAdjustedEarningsThenTitleThenArtist() {
        return Comparator.comparingDouble(Track::getEarnings)
                .thenComparing(Track::getTitle)
                .thenComparing(Track::getArtist);
    }

    public static BinaryOperator<Track> mergeDuplicates() {
        return (t1, t2) -> {
            Track merged = new Track();
            merged.setTrackId(t1.getTrackId());

            merged.setEarnings(t1.getEarnings() + t2.getEarnings());
            merged.setReleaseYear(Math.min(t1.getReleaseYear(), t2.getReleaseYear()));
            merged.setRating(Math.max(t1.getRating(), t2.getRating()));

            merged.setArtist(pickString(t1.getArtist(), t2.getArtist()));
            merged.setTitle(pickString(t1.getTitle(), t2.getTitle()));
            merged.setGenre(pickString(t1.getGenre(), t2.getGenre()));

            Set<String> unionTags = new LinkedHashSet<>();
            unionTags.addAll(t1.getTags());
            unionTags.addAll(t2.getTags());
            merged.setTags(new ArrayList<>(unionTags));

            return merged;
        };
    }

    private static String pickString(String s1, String s2) {
        if (s1 == null || s1.isEmpty()) return s2;
        if (s2 == null || s2.isEmpty()) return s1;
        return s1.compareTo(s2) <= 0 ? s1 : s2;
    }
}

class TrackManager {
    private List<Track> trackList = new ArrayList<>();

    public List<Track> getTrackList() { return trackList; }
    public void setTrackList(List<Track> trackList) { this.trackList = trackList; }

    public void addTrack(Track track) {
        trackList.add(track);
    }

    public void resolveDuplicatesById() {
        trackList = new ArrayList<>(
                trackList.stream()
                        .collect(Collectors.toMap(
                                Track::getTrackId,
                                Function.identity(),
                                TrackUtil.mergeDuplicates()
                        ))
                        .values()
        );
    }

    public List<Track> getEligibleTracks(int year, double baseCommission,
                                        Predicate<Track> extraFilter)
            throws NoEligibleTrackException {

        List<Track> eligible = trackList.stream()
                .filter(TrackUtil.isReleasedBefore(year))
                .filter(extraFilter != null ? extraFilter : t -> true)
                .peek(t -> {
                    double adjusted = TrackUtil.computeAdjustedEarnings(year)
                            .apply(t, baseCommission);
                    TrackUtil.applyAdjustedEarnings(adjusted).accept(t);
                })
                .collect(Collectors.toList());

        if (eligible.isEmpty())
            throw new NoEligibleTrackException("No tracks released before " + year + " to apply commission");

        return eligible;
    }

    public List<Track> getTracksSortedByAdjustedEarningsTitleArtist() {
        return trackList.stream()
                .sorted(TrackUtil.byAdjustedEarningsThenTitleThenArtist())
                .collect(Collectors.toList());
    }

    public Stream<Track> getMinEarningTracks() {
        OptionalDouble min = trackList.stream()
                .mapToDouble(Track::getEarnings)
                .min();

        return min.isPresent()
                ? trackList.stream().filter(t -> t.getEarnings() == min.getAsDouble())
                : Stream.empty();
    }

    public Map<String, DoubleSummaryStatistics> getGenreWiseStats() {
        return trackList.stream()
                .collect(Collectors.groupingBy(
                        Track::getGenre,
                        Collectors.summarizingDouble(Track::getEarnings)
                ));
    }

    public Map<Boolean, List<Track>> partitionByRating(double threshold) {
        return trackList.stream()
                .collect(Collectors.partitioningBy(t -> t.getRating() >= threshold));
    }

    public List<Track> getTopKByGenre(String genre, int k) {
        return trackList.stream()
                .filter(t -> t.getGenre().equalsIgnoreCase(genre))
                .sorted(Comparator.comparingDouble(Track::getEarnings).reversed())
                .limit(k)
                .collect(Collectors.toList());
    }
}


public class UserInterface {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TrackManager manager = new TrackManager();

        try {
            System.out.println("Enter the number of tracks");
            int n = Integer.parseInt(sc.nextLine());

            System.out.println("Enter the details for each track");
            for (int i = 0; i < n; i++) {
                String line = sc.nextLine();
                try {
                    manager.addTrack(TrackUtil.parseTrack(line));
                } catch (InvalidTrackException e) {
                    System.out.println("InvalidTrackException: " + e.getMessage());
                }
            }

            manager.resolveDuplicatesById();

            System.out.println("Enter the release year to filter tracks released before that year");
            int year = Integer.parseInt(sc.nextLine());

            System.out.println("Enter the base commission percentage");
            double baseCommission = Double.parseDouble(sc.nextLine());

            System.out.println("Enter the rating threshold");
            double ratingThreshold = Double.parseDouble(sc.nextLine());

            Predicate<Track> extraFilter = t -> t.getRating() >= ratingThreshold;

            List<Track> eligible = manager.getEligibleTracks(year, baseCommission, extraFilter);
            System.out.println("Eligible Tracks:");
            eligible.forEach(System.out::println);

        } catch (NoEligibleTrackException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }
}