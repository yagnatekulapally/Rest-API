import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import files.Payload;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import files.putpayload;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import java.util.Date;
import java.lang.*; 


public class post_test {
	Properties prop=new Properties();
	@BeforeTest
	public void getData() throws IOException
	{
		prop.getProperty("HOST");
		FileInputStream fis=new FileInputStream("C:\\Users\\ystekulapally\\workspace\\RAT\\src\\files\\env.properties");
		prop.load(fis);
	}
	@Test
	public void postData() throws InterruptedException{

		RestAssured.baseURI=prop.getProperty("HOST");
		RestAssured.basePath="/symphony-uiserver/api/v1/";
		Response res =(Response) given().
		header("Content-Type","application/json").
		body(Payload.body()).
		when().
		post("/contract");
		res.then().assertThat().statusCode(200).and().contentType(ContentType.JSON);
		//Thread.sleep(1000*60*4);
		
		//Grab the ContractRootID from response
		String responseString=res.getBody().asString();
		System.out.println(responseString);
		JsonPath js =new JsonPath(responseString);
		String ContractRootID=js.get("Contract.ContractRootID").toString();
		System.out.println(ContractRootID);
		
		/*//added for populate body string with ContractRootID
		//String strputBody = 
		 putpayload.putpayloadbody().replace("$ContractRootID$", ContractRootID);
		System.out.println(ContractRootID);
		
		//Take the ContractRootID and place it in put command
		given().
		header("Content-Type","application/json").
		body(putpayload.putpayloadbody()).
		when().
		put("/contract/"+ContractRootID).
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON);
		
		//Grab the ContractRootID and place it in get command
		
		given().
		header("Content-Type","application/json").
		when().
		get("/contract/"+ContractRootID).
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		body("Contract.ContractRootID",equalTo(ContractRootID)).and()
		.body("Contract.DeliverySchedules[0].DeliveryScheduleItems[0].DeliveryLotID", equalTo(13186)).and()
		.body("Contract.DeliverySchedules[0].DeliveryScheduleItems[0].DeliveryDateFrom", equalTo("2018-01-01T00:00:00"));*/
	}
	}


