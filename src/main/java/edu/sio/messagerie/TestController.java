package edu.sio.messagerie;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import edu.sio.messagerie.models.Organization;

@Controller
public class TestController {

	@Autowired
	private OrgaRepository repository;

	@GetMapping("/orga")
	public String index(ModelMap model) {
		List<Organization> orgas = repository.findAll();
		model.put("orgas", orgas);
		return "/orga/index";
	}

	@GetMapping("/orga/addNew")
	public String addNew() {
		return "/orga/addNew";
	}

	@PostMapping("/orga/addNew")
	public RedirectView addNewSubmit(@RequestParam String name, RedirectAttributes attrs) {
		Organization orga = new Organization();
		orga.setName(name);
		repository.save(orga);
		attrs.addFlashAttribute("msg", "Organisation ajoutée : <strong>" + orga + "</strong>");
		return new RedirectView("/orga");
	}

	@PostMapping("/orga/delete/{id}")
	public RedirectView delete(@PathVariable int id, RedirectAttributes attrs) {
		Optional<Organization> optOrga = repository.findById(id);

		if (optOrga.isPresent()) {
			Organization orga = optOrga.get();
			repository.delete(orga);
			attrs.addFlashAttribute("msg", "Organisation supprimée : <strong>" + orga + "</strong>");
		}
		return new RedirectView("/orga");
	}

}
