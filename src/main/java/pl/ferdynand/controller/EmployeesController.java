package pl.ferdynand.controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.ferdynand.services.Base64Service;
import pl.ferdynand.ui.model.Employee;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("api/weeia/vcard/users")
public class EmployeesController {
    @Value("${vcard.baseUrl}")
    private String baseUrl;
    @Autowired
    private Base64Service base64Service;

    @GetMapping
//    public ResponseEntity<List<Employee>> getUsers(@RequestParam(value = "search", defaultValue = "", required = false) String search,
    public String getUsers(@RequestParam(value = "search", defaultValue = "", required = false) String search,
                                                final Model model) {
        try {
            String queryString = baseUrl + encodeUrl(search);
            System.out.println(queryString);

            Document employeesDoc = Jsoup.connect(queryString).get();
//            Document employeesDoc = Jsoup.connect(placesURL.format(URLEncoder.encode(queryString, "UTF-8"));

            Elements employeeElements = employeesDoc.select(".user-info");
            if (employeeElements.isEmpty()){
                model.addAttribute("search", search);
                return "empty";
            }

//            Element element = employeesDoc.select(".user-info").first();
            List<Employee> employees = new ArrayList<>();

            employeeElements.forEach(employee -> {
                Employee newEmployee = new Employee();

                newEmployee.setFullName(employee.selectFirst("h3").text());
                newEmployee.setOrganizationUnit(employee.selectFirst(".item-content").text());
                newEmployee.setProfileHref(employee.selectFirst("a.fullprofile-link").attr("href"));
                newEmployee.setvCardLink(
                        "/vcard/" + base64Service.base64Encode(newEmployee.getFullName()+"\n"+newEmployee.getOrganizationUnit())
                );

                employees.add(newEmployee);
            });

            model.addAttribute("employees", employees);

            return "employees";

        } catch (IOException e){
//            return new ResponseEntity<>(Collections.singletonList(new Employee("ERROR", e.getLocalizedMessage(), "")) , HttpStatus.BAD_REQUEST);
            return "error";
        }
    }

    private String encodeUrl(String search) {
        try {
            return URLEncoder.encode(search, StandardCharsets.UTF_8.toString());
        } catch ( UnsupportedEncodingException e) {
            Logger.getLogger(getClass().getName()).log(Level.WARNING, e.getLocalizedMessage());
            return e.getLocalizedMessage();
        }
    }

}
