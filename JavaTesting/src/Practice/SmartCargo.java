package Practice;
import java.util.Scanner;

public class SmartCargo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		Utility util = new Utility();
		String input = sc.nextLine();
		String shipmentId = input.split(":")[0];
		
		if(!util.validateShipmentId(shipmentId)) {
			System.out.println("Shipment id "+shipmentId+" is invalid");
			System.out.println("Please provide a valid record");
	       return; 
		}
		Shipment shipment = util.parseDetails(input);
		System.out.println("Shipment id : " + shipment.getShipmentId());
        System.out.println("Date of shipment : " + shipment.getShipmentDate());
        System.out.println("Rating : " + shipment.getShipmentRating());
        if(shipment instanceof SteelShipment) {
        	System.out.println("Steel quantity : "+((SteelShipment) shipment).getSteelQuantity());
        	
        }
        System.out.println("Vehicle selected : "+shipment.vehicleSelection());
        System.out.println("Total charge : "+shipment.calculateTotalCharge());
        
		
		

	}

}
abstract class Shipment{
	String shipmentId;
	String shipmentDate;
	int shipmentRating;
	
	public Shipment(String shipmentId, String shipmentDate, int shipmentRating) {
		this.shipmentId = shipmentId;
		this.shipmentDate = shipmentDate;
		this.shipmentRating = shipmentRating;
	}
	public String getShipmentId() {
		return shipmentId;
	}
	public void setShipmentId(String shipmentId) {
		this.shipmentId = shipmentId;
	}
	public String getShipmentDate() {
		return shipmentDate;
	}
	public void setShipmentDate(String shipmentDate) {
		this.shipmentDate = shipmentDate;
	}
	public int getShipmentRating() {
		return  shipmentRating;
	}
	public void setShipmentRating(int shipmentRating) {
		this.shipmentRating = shipmentRating;
	}
	
	abstract public String vehicleSelection();
	abstract public float calculateTotalCharge();
}
class SteelShipment extends Shipment{
	private float steelWeight;
	private int steelQuantity;
	private float steelPrice;
	
	public float getSteelPrice() {
		return steelPrice;
	}
	public void setSteelPrice(float steelPrice) {
		this.steelPrice = steelPrice;
	}
	public float getSteelWeight() {
		return steelWeight;
	}
	public void setSteelWeight(float steelWeight) {
		this.steelWeight = steelWeight;
	}
	public int getSteelQuantity() {
		return steelQuantity;
	}
	public void setSteelQuantity(int steelQuantity) {
		this.steelQuantity = steelQuantity;
	}
	
	SteelShipment(String shipmentId, String shipmentDate, int shipmentRating, float steelWeight, int steelQuantity, float steelPrice ){
		super(shipmentId, shipmentDate, shipmentRating);
		this.steelWeight = steelWeight;
		this.steelQuantity = steelQuantity;
		this.steelPrice = steelPrice;
	}
	public String vehicleSelection() {
		if(steelQuantity < 200) {
			return "MiniTruck";
		}else if(steelQuantity <= 500) {
			return "Truck";
		}else {
			return "HeavyTrcuk";
		}
	}
	public float calculateTotalCharge() {
	    float price = steelPrice * steelQuantity;
	    float tax = price * 0.30f;
	    float vehiclePrice = 0;
	    float discount = 0;

	    // Vehicle price selection
	    if (vehicleSelection().equalsIgnoreCase("MiniTruck")) {
	        vehiclePrice = 800f;
	    } else if (vehicleSelection().equalsIgnoreCase("Truck")) {
	        vehiclePrice = 1500f;
	    } else if (vehicleSelection().equalsIgnoreCase("HeavyTruck")) {
	        vehiclePrice = 2800f;
	    }

	    // Discount based on rating
	    if (shipmentRating == 5) {
	        discount = price * 0.2f;
	    } else if (shipmentRating == 3 || shipmentRating == 4) {
	        discount = price * 0.1f;
	    }

	    float totalCharge = price + tax + vehiclePrice - discount;
	    return totalCharge;
	}	
	
}
class FurnitureShipment extends Shipment{
	
	private float furnitureLength;
	private float furnitureWidth;
	private String furnitureType; // "Premium" / "NonPremium"
	private float furniturePrice;
	public float getFurnitureLength() {
		return furnitureLength;
	}
	public void setFurnitureLength(float furnitureLength) {
		this.furnitureLength = furnitureLength;
	}
	public float getFurnitureWidth() {
		return furnitureWidth;
	}
	public void setFurnitureWidth(float furnitureWidth) {
		this.furnitureWidth = furnitureWidth;
	}
	public String getFurnitureType() {
		return furnitureType;
	}
	public void setFurnitureType(String furnitureType) {
		this.furnitureType = furnitureType;
	}
	public float getFurniturePrice() {
		return furniturePrice;
	}
	public void setFurniturePrice(float furniturePrice) {
		this.furniturePrice = furniturePrice;
	}
	public FurnitureShipment(String shipmentId, String shipmentDate, int shipmentRating,
			float furnitureLength,float furnitureWidth,String furnitureType,float furniturePrice){
		super(shipmentId, shipmentDate, shipmentRating);
		this.furnitureLength=furnitureLength;
		this.furnitureWidth = furnitureWidth;
		this.furnitureType = furnitureType;
		this.furniturePrice = furniturePrice;
		
	}
	
	public String vehicleSelection() {
		float area = furnitureLength * furnitureWidth;
		if(area < 150) {
			return "MiniTruck";
		}else if(area <= 300) {
			return "Truck";
		}else {
			return "HeavyTruck";
		}
	}
		public float calculateTotalCharge() {
			float volume = furnitureLength * furnitureWidth;
			float vehiclePrice = 0;
		    float discount = 0;
		    float furnitureTypeRate;
		    if (furnitureType.equalsIgnoreCase("Premium")) {
		        furnitureTypeRate = 0.30f;
		    } else {
		        furnitureTypeRate = 0.18f;
		    }
		    
		    float price = volume * furniturePrice * furnitureTypeRate;
		    float Tax = price * 0.30f;

		    // Vehicle price selection
		    if (vehicleSelection().equalsIgnoreCase("MiniTruck")) {
		        vehiclePrice = 800f;
		    } else if (vehicleSelection().equalsIgnoreCase("Truck")) {
		        vehiclePrice = 1500f;
		    } else if (vehicleSelection().equalsIgnoreCase("HeavyTruck")) {
		        vehiclePrice = 2800f;
		    }

		    // Discount based on rating
		    if (shipmentRating == 5) {
		        discount = price * 0.2f;
		    } else if (shipmentRating == 3 || shipmentRating == 4) {
		        discount = price * 0.1f;
		    }
		    float TotalCharge = (price + vehiclePrice + Tax) - discount;
		    return TotalCharge;   
	}
}
class Utility{
	public Shipment parseDetails(String input) {
		String[] data = input.split(":");
		String shipmentId = data[0];
		String shipmentDate = data[1];
		int shipmentRating = Integer.parseInt(data[2]);
	    String shipmentType = data[3];
	    if(shipmentType.equalsIgnoreCase("SteelShipment")) {
	    	float steelWeight = Float.parseFloat(data[4]);
	    	int steelQuantity = Integer.parseInt(data[5]);
	    	float steelPrice = Float.parseFloat(data[6]);
	    	
	    	return new SteelShipment(
	    		  shipmentId,
                  shipmentDate,
                  shipmentRating,
                  steelWeight,
                  steelQuantity,
                  steelPrice);
	    	}else if(shipmentType.equalsIgnoreCase("FurnitureShipment")) {
	    		float furnitureLength = Float.parseFloat(data[4]);
	    		float furnitureWidth = Float.parseFloat(data[5]);
	    		String furnituretype = data[6];
	    		float furniturePrice = Float.parseFloat(data[7]);
	    		
	    		return new FurnitureShipment(
	                    shipmentId,
	                    shipmentDate,
	                    shipmentRating,
	                    furnitureLength,
	                    furnitureWidth,
	                    furnituretype,
	                    furniturePrice
	            );
	    			
	    		
	    	}
	    return null;
	    }
	public boolean validateShipmentId(String shipmentId) {
		return shipmentId.matches("^SHP\\d{3}[A-Z]$");
	}
	public String  findObjectType(Shipment shipment) {
		if(shipment instanceof SteelShipment) {
			return "SteelShipment";
		}else if(shipment instanceof FurnitureShipment) {
			return "FurnitureShipment";
		}
		return "Unknown";
	}
		
}
	

