package inspringboot.tasktrckerexercise.Controller;

import inspringboot.tasktrckerexercise.ApiResponse.ApiResponse;
import inspringboot.tasktrckerexercise.Model.TaskTracker;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RequestMapping("/api/v1/tasktracker")
@RestController
public class TaskTrackerController {

    ArrayList<TaskTracker> taskTrackers = new ArrayList<>();

    @PutMapping("/add")
    public ApiResponse createNewTask(TaskTracker taskTracker){
        return new ApiResponse("added successfully");
    }

    @GetMapping("/display")
    public ArrayList<TaskTracker> displayAllTasks(){
        return taskTrackers ;
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateTask(@PathVariable int index,TaskTracker taskTracker){
        taskTrackers.set(index , taskTracker);
        return new ApiResponse("update successfully");
    }

    @DeleteMapping("/remove/{index}")
    public ApiResponse deleteTask(@PathVariable int index){
        taskTrackers.remove(index);
        return new ApiResponse("remove successfully");
    }


    @PatchMapping("/status/{index}")
    public ApiResponse changeTaskStatus(@PathVariable int index, @RequestParam boolean status){
        TaskTracker t = taskTrackers.get(index);
        t.setStatus(status);
        return new ApiResponse("Status changed to " + status);
    }

    @GetMapping("/search")
    public Object searchByTitle(@RequestParam String title){
        for (TaskTracker t : taskTrackers){
            if (t.getTitle().equalsIgnoreCase(title)){
                return t;
            }
        }
        return null;
    }





}
