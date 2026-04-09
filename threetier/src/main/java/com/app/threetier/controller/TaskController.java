    package com.app.threetier.controller;

    import com.app.threetier.domain.vo.TaskVO;
    import com.app.threetier.service.TaskService;
    import lombok.RequiredArgsConstructor;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RequestMapping;

    @Controller
    @RequestMapping("/tasks/*")
    @RequiredArgsConstructor
    public class TaskController {

        private final TaskService taskService;

        @GetMapping("register")
        public void register(TaskVO taskVO) {}

        @GetMapping("result")
        public String getTotal(TaskVO taskVO, Model model) {
            TaskVO result = taskService.getTotalAndAverage(taskVO);
         model.addAttribute("task", result);
            return "tasks/result";
        }
    }
