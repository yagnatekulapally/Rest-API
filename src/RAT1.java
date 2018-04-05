import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;



import org.testng.annotations.Test;
public class RAT1 {
	@Test
	public void  test1(){
		// TODO Auto-generated method stub
		

	RestAssured.baseURI="http://www.google.com";
	RestAssured.basePath="/extenstion";
	given().
	param("Content-Type","application/json").
	when().
	get("/data/1234").
	/*then().body("Contract.ContractRootID", equalTo(2517)).and()
	.body("Contract.DeliverySchedules[0].DeliveryScheduleID", equalTo(1639)).and().
	body("Contract.DeliverySchedules[0].DeliveryScheduleItems[0].DeliveryLotID", equalTo(13186)).and()
	.body("Contract.DeliverySchedules[0].DeliveryScheduleItems[0].DeliveryDateFrom", equalTo("2018-01-01T00:00:00"));*/
	
	then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
	body("Contract.ContractRootID",equalTo(38031)).and()
	.body("Contract.DeliverySchedules[0].DeliveryScheduleItems[0].DeliveryLotID", equalTo(13186)).and()
	.body("Contract.DeliverySchedules[0].DeliveryScheduleItems[0].DeliveryDateFrom", equalTo("2018-02-01T00:00:00"));
	
	}

}
