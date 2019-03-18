package pl.ls.zad_20;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    private List<Fruit> fruits;
    private double sum;
    private HomeController homeControl;

    public HomeController() {
        fruits = new ArrayList<>();
        fruits.add(new Fruit("Banan", 4.85));
        fruits.add(new Fruit("Gruszka", 3.40));
        fruits.add(new Fruit("Jabłka", 1.99));
        fruits.add(new Fruit("Annanas", 5.55));
    }

    @GetMapping("/")
    public String tabelkaFrutis(Model model) {
        model.addAttribute("listFruit", fruits);
        model.addAttribute("newFruit", new Fruit());
        return "form";
    }

    @PostMapping("/addFruit")
    public String add(Fruit fruit) {
        fruits.add(fruit);
        return "redirect:/lista";
    }

    @GetMapping("/tabela")
    public String tabelkadrukFrutis(Model model) {
        sum = sumPrice(fruits);
        model.addAttribute("suma", sum);
        model.addAttribute("listFruit", fruits);
        return "tabPrint";
//        return "tabPrint";
    }

    private double sumPrice(List<Fruit> fruits) {
        sum = 0;
        for (Fruit fruit : fruits) {
            sum += fruit.getPrice();
        }
        return sum;
    }

    @GetMapping("/lista")
    public String Listarutis(Model model) {

        model.addAttribute("suma", sumPrice(fruits));
        model.addAttribute("listFruit", fruits);
        return "listaDruk";
//        return "tabPrint";
    }

}
