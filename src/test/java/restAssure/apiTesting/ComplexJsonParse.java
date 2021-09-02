package restAssure.apiTesting;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

public class ComplexJsonParse {

    @Test
    public static void complexJsonParse() {
        JsonPath js = new JsonPath(Utility.courseDetails());

        //print all the courses
        int count = js.getInt("courses.size()");
        System.out.println(count);

        //print purchase amount
        int totalAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println(totalAmount);

        //print title of the firstCourse
        String titleOfFirstCourse = js.get("courses[0].title");
        System.out.println(titleOfFirstCourse);

        //print title of the thirdCourse
        String titleOfThirdCourse = js.get("courses[2].title");
        System.out.println(titleOfThirdCourse);

        //print All course titles and their respective prices
        for (int i = 0; i < count; i++) {
            String courseTitles = js.get("courses[" + i + "].title");
            System.out.println(js.get("courses[" + i + "].price").toString());
            System.out.println(courseTitles);
        }
        System.out.println("print no.of copies sold by RPA course");
        for (int i=0; i<count; i++){
            String courseTitles = js.get("courses[" + i + "].title");
            if(courseTitles.equalsIgnoreCase("RPA")){
              int copies =  js.get("courses[" + i + "].copies");
              System.out.println(copies);
            }
        }
    }
}

