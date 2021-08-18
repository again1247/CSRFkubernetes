package security.k8s.spring;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexPage {

	@GetMapping(path = { "/", "/index" })
	public String index(Model model) {

		String result = null;
		try {
			result = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			result = "";
		}

		model.addAttribute("ip", result);

		return "index";
	}

}