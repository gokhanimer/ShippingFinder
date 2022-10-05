package tracker;

import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ShippingFinder{
	
	
	public static void main(String[] args) throws InterruptedException {
		
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter shipment ID:");
        String shipID = scan.nextLine();               
        new ShippingFinder().Finder(shipID);
        
	}
	
	public static boolean Schenker (String shipID) throws InterruptedException {
		ChromeOptions opt = new ChromeOptions();
        
        opt.addArguments("--headless");
        WebDriver driver = new ChromeDriver(opt);
		String url = "https://www.dbschenker.com/se-sv/om-oss/kundservice/spara-och-sok?reference_number="+ shipID +"&language_region=sv_SE";
        
        driver.get(url);
        Thread.sleep(1000);
        WebElement cont = driver.findElement(By.id("swedish-tracking"));                
        
        boolean exists = cont.getText().contains(shipID);        
		return exists;
	}
	
	public static boolean PostNord (String shipID) throws InterruptedException {
		ChromeOptions opt = new ChromeOptions();
        
        opt.addArguments("--headless");
        WebDriver driver = new ChromeDriver(opt);
		String url = "https://www.postnord.se/vara-verktyg/spara-brev-paket-och-pall?shipmentId=" + shipID;
        
        driver.get(url);
        Thread.sleep(1000);
        WebElement cont = driver.findElement(By.xpath("//postnord-widget-tracking"));        
        
        boolean exists = cont.getText().contains(shipID);
		return exists;
	}
	
	public void Finder(String shipID) throws InterruptedException {
		ShippingFinder.Schenker(shipID);
    	ShippingFinder.PostNord(shipID);
    	
		if (Schenker(shipID)) {
			System.out.println("Your package is handeled by Schenker");
		}			
		else if(PostNord(shipID)) {				
			System.out.println("Your package is handeled by PostNord");
		} else {
			System.out.println("Your package is not handeled by Schenker or PostNord");
		}
		
		System.out.println("---Service Terminated---");
	}
	


}
