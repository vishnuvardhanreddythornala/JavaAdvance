package Practice;
import java.util.ArrayList;
import java.util.List;

public class Tester {

    static class Participant {
        private String name;
        private String talent;
        private double averageScore;

        public Participant(String name, String talent, double averageScore) {
            this.name = name;
            this.talent = talent;
            this.averageScore = averageScore;
        }

        public String getName() {
            return name;
        }

        public String getTalent() {
            return talent;
        }

        public double getAverageScore() {
            return averageScore;
        }

        @Override
        public String toString() {
            return "Participant(\"" + name + "\", \"" + talent + "\", " + averageScore + ")";
        }
    }

    static class AmazingTalent {

        public static ArrayList<Participant> generateListOfFinalists(Participant[] finalists) {
            ArrayList<Participant> list = new ArrayList<>();

            for (Participant p : finalists) {
                list.add(p);
            }

            return list;
        }

        public static ArrayList<Participant> getFinalistsByTalent(List<Participant> finalists, String talent) {
            ArrayList<Participant> filteredList = new ArrayList<>();

            for (Participant p : finalists) {
                if (p.getTalent().equalsIgnoreCase(talent)) {
                    filteredList.add(p);
                }
            }

            return filteredList;
        }
    }

    // ✅ main method
    public static void main(String[] args) {

        Participant[] finalists = {
                new Participant("Hazel", "Singing", 91.2),
                new Participant("Ben", "Instrumental", 95.7),
                new Participant("John", "Singing", 94.5),
                new Participant("Bravo", "Singing", 97.6)
        };

        ArrayList<Participant> allFinalists =
                AmazingTalent.generateListOfFinalists(finalists);

        System.out.println("All Finalists:");
        for (Participant p : allFinalists) {
            System.out.println(p);
        }

        System.out.println("\nSinging Finalists:");
        ArrayList<Participant> singers =
                AmazingTalent.getFinalistsByTalent(allFinalists, "Singing");

        for (Participant p : singers) {
            System.out.println(p);
        }
    }
}
