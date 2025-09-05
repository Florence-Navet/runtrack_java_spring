package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/admin").setViewName("admin");
    }
}


//    @Controller
//    public class ViewController {
//        private final PersonService personService;
//
//        public ViewController(PersonService personService) {
//            this.personService = personService;
//        }
//
//        @GetMapping("/view")
//        public String afficherMots(Model model) {
//            model.addAttribute("message", "Coucou c'est le jour 2 !!!!");
//            model.addAttribute("mots", List.of("ça me saoule grave", "Olivier", "Toulon", "Adeline", "Blabla"));
//            model.addAttribute("formData", new FormData());
//            List<Person> bidule = personService.findAll();
//            model.addAttribute("persons", bidule);
//            return "view";
//        }
//
//        //Test pour connexion
//        @GetMapping("/coco")
//        @ResponseBody
//        public String zoneProtegee() {
//            return "Bienvenue dans la zone coco !";
//        }
//
//        @GetMapping("/admin")
//        @ResponseBody
//        public String adminZone() {
//            return "Bienvenue dans la zone ADMIN !";
//        }
//
//        @GetMapping("/user")
//        @ResponseBody
//        public String userZone() {
//            return "Bienvenue dans la zone user !";
//        }
//
//        @GetMapping("/login")
//        public String loginPage(@RequestParam(value = "error", required = false) String error,
//                                @RequestParam(value = "logout", required = false) String logout,
//                                Model model) {
//            if (error != null)  model.addAttribute("errorMsg", "Identifiants invalides.");
//            if (logout != null) model.addAttribute("logoutMsg", "Vous avez été déconnecté.");
//            return "login"; // => templates/login.html
//        }
//
//
//        @PostMapping("/view")
//        public String handleForm(@Valid @ModelAttribute("formData") FormData formData,
//                                 BindingResult errors,
//                                 Model model) {
//            // Ré-alimente le modèle
//            model.addAttribute("message", "Coucou c'est le jour 2 !!!!");
//            model.addAttribute("mots", List.of("ça me saoule grave", "Olivier", "Toulon", "Adeline", "Blabla"));
//
//            if (errors.hasErrors()) {
//
//                return "view";
//
//            }
//
//            Person person = new Person(formData.getWelcome(), formData.getAge());
//            personService.save(person);
//
//            List<Person> bidule = personService.findAll();
//            model.addAttribute("persons", bidule);
//
//            model.addAttribute("welcomeMsg",
//                    "Bienvenue, " + formData.getWelcome() + " ! Tu as " + formData.getAge() + " ans.");
//
//
//            return "view";
//        }

        // MISE À JOUR (POST /view/{id}/update)
    //    @PostMapping("/view/{id}/update")
    //    public String updatePerson(@PathVariable Long id,
    //                               @RequestParam String name,
    //                               @RequestParam Integer age) {
    //        personService.findById(id).ifPresent(p -> {
    //            p.setName(name);
    //            p.setAge(age);
    //            personService.save(p);
    //        });
    //        return "redirect:/view";
//    }
//}
