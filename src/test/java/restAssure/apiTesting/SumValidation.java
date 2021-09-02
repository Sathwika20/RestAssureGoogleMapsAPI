package restAssure.apiTesting;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SumValidation {
    @Test
    public void sumOfAllCourses(){
        JsonPath js = new JsonPath(Payload.courseDetails());
        int count = js.getInt("courses.size()");
        int sum = 0;
        for (int i=0; i<count; i++){
            int prices = js.getInt("courses["+i+"].price");
            int copies = js.getInt("courses["+i+"].copies");
            int amount = prices*copies;
            System.out.println(amount);
            sum = sum + amount;
        }
        System.out.println(sum);
        int purchaseAmount = js.getInt("dashboard.purchaseAmount");
        Assert.assertEquals(sum,purchaseAmount);
    }
}
