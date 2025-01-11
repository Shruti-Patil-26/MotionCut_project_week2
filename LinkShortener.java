import java.util.HashMap;
import java.util.Scanner;

public class LinkShortener {
	
	final String baseUrl="http://short.url/";
	int hashLength=3;
	HashMap<String,String> shortToUrl;
	HashMap<String,String> urlToShort;
	
	public LinkShortener() {
		urlToShort=new HashMap<>();
		shortToUrl=new HashMap<>();
	}

	String generateHash(String link)
	{
		return Integer.toHexString(link.hashCode()).substring(0, hashLength);
	}
	
	String shortenUrl(String link)
	{
		if(urlToShort.containsKey(link))
		{
			return baseUrl+urlToShort.get(link);
		}
		
		String hash;
		do
		{
			hash=generateHash(link);
		}while(shortToUrl.containsKey(hash));
		
		urlToShort.put(link, hash);
		shortToUrl.put(hash, link);
		return baseUrl+hash;
	}
	
	String expandUrl(String link)throws IllegalArgumentException
	{
		if(!link.startsWith(baseUrl))
		{
			throw new IllegalArgumentException("Invalid short url format");
		}
		String hash=link.replace(baseUrl, "");
		if(!shortToUrl.containsKey(hash))
			throw new IllegalArgumentException("Short url does not exist");
		
		return shortToUrl.get(hash);
	}
	
	public static void main(String[] args) {

		System.out.println("Welcome to link shortener system ");
		
		Scanner sc=new Scanner(System.in);
		LinkShortener links=new LinkShortener();
		boolean flag=false;
		while(!flag)
		{
			System.out.println("1.Shorten a URL");
			System.out.println("2. Expand a URL");
            System.out.println("3. Exit");
            System.out.println("Enter your choice");
            int choice=sc.nextInt();
            sc.nextLine();
            switch (choice) {
			case 1:
				System.out.println("Enter long url: ");
				String longurl=sc.nextLine();
				String shortu=links.shortenUrl(longurl);
				System.out.println("Shortened URL is : "+shortu);
				
				break;
			case 2:
				System.out.println("Enter short url: ");
				String url=sc.nextLine();
				try
				{
					String expandedUrl=links.expandUrl(url);
					System.out.println("Expanded url is : "+expandedUrl);
				}
				catch (IllegalArgumentException e) {
					System.out.println("Error: "+e.getMessage());
				}
				break;
			case 3:
					flag=true;
					System.out.println("Thank you....");
					break;

			default:
				break;
			}
		}

	}

}
