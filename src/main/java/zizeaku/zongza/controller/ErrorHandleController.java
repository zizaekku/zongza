package zizeaku.zongza.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/error")
public class ErrorHandleController implements ErrorController{
    
    @ExceptionHandler(Throwable.class)
    @GetMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String statusMsg = status.toString();
        
        HttpStatus httpStatus = HttpStatus.valueOf(Integer.valueOf(statusMsg));
        
        model.addAttribute("msg", statusMsg + " " + httpStatus.getReasonPhrase());
        
        return "thymeleaf/error";
    }
    
    // @Override
    // public String getErrorPath() {
    //     // TODO Auto-generated method stub
    //     return "/error";
    // }
}
