package helpdesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import helpdesk.model.Ticket;
import helpdesk.repository.TicketRepository;

@Controller
public class TicketController {

    @Autowired
    private TicketRepository repository;

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("ticket", new Ticket());

        return "index";
    }

    @PostMapping("/save")
    public String saveTicket(@ModelAttribute Ticket ticket) {

        ticket.setStatus("Open");

        repository.save(ticket);

        return "redirect:/tickets";
    }

    @GetMapping("/tickets")
    public String viewTickets(Model model) {

        model.addAttribute("tickets",
                repository.findAll());

        return "tickets";
    }

    @GetMapping("/resolve/{id}")
    public String resolve(@PathVariable Long id) {

        Ticket ticket =
                repository.findById(id).orElse(null);

        if(ticket != null) {
            ticket.setStatus("Resolved");
            repository.save(ticket);
        }

        return "redirect:/tickets";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        repository.deleteById(id);

        return "redirect:/tickets";
    }
}