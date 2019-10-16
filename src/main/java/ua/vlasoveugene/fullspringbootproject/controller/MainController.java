package ua.vlasoveugene.fullspringbootproject.controller;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ua.vlasoveugene.fullspringbootproject.entity.Message;
import ua.vlasoveugene.fullspringbootproject.entity.User;
import ua.vlasoveugene.fullspringbootproject.service.GreetingService;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


/**
 * The Main Controller of this app.
 */
@Controller
public class MainController {
    private final GreetingService service;

    @Value("${upload.path}")
    private String uploadPath;

    public MainController(GreetingService service) {
        this.service = service;
    }

    /**
     * Greeting string.
     *
     * @param name  the name
     * @param model the model
     * @return the string
     */
    @GetMapping("/")
    public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name,
                           Model model){
        model.addAttribute("user", name);
        return "greeting";
    }

    @GetMapping("/main")
    public String getMainPage(@RequestParam(required = false) String filter,
                              Model model){
        Iterable<Message> result;
        if(filter!=null&&!filter.isEmpty())
            result = service.getAllMessagesByTag(filter);
        else
            result = service.getAllMessages();
        model.addAttribute("filter", filter);
        model.addAttribute("messages",result);
        return "main";
    }

    @PostMapping("/main")
    public String addMessage(@AuthenticationPrincipal User user,
                             @RequestParam(required = false) String text,
                             @RequestParam(required = false) String tag,
                             @RequestParam("file") MultipartFile file) throws IOException {
        Message message = new Message();

        /*
        * Ниже кусок кода для загрузки файла в директорию uploadPath и прибавления названия файла к Message
        */
        if(file!=null&&!file.getOriginalFilename().isEmpty()){
        File uploadDir = new File(uploadPath);

            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }

            String uiidFile = UUID.randomUUID().toString();
            String resultFileName = uiidFile+"."+file.getOriginalFilename();

            file.transferTo(new File(uploadPath+"/"+resultFileName));

            message.setFilename(resultFileName);
        }

        if(!tag.isEmpty()&&!text.isEmpty()){
            message.setTag(tag);
            message.setText(text);
            message.setAuthor(user);
        }
        service.saveMessage(message);
        return "redirect:/main";
    }
}
