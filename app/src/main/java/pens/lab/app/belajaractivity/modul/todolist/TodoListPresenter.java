package pens.lab.app.belajaractivity.modul.todolist;

import java.util.ArrayList;

import pens.lab.app.belajaractivity.data.model.Task;

/**
 * Created by fahrul on 13/03/19.
 */

public class TodoListPresenter implements TodoListContract.Presenter{
    private final TodoListContract.View view;
    ArrayList<Task> data = new ArrayList<Task> ();


    public TodoListPresenter(TodoListContract.View view) {
        this.view = view;
    }
    @Override
    public void start() {}

    @Override
    public ArrayList<Task> getDataSet() {
        //get Data from DB
//        ArrayList<Task> data = new ArrayList<Task> ();
        data.add(new Task("1","Task 1", "Kerjakan task satu"));
        data.add(new Task("2", "Task 2", "Kerjakan task dua"));
        return data;
    }

    @Override
    public void saveData(Task task) {

        data.add(task);
    }

    @Override
    public Task getTask(int position) {
        return data.get(position);
    }

    @Override
    public void deleteTask(int position) {
        data.remove(position);
    }

    @Override
    public void updateTask(int position, Task newTask) {
        Task task = data.get(position);
        task.setId(newTask.getId());
        task.setTitle(newTask.getTitle());
        task.setDescription(newTask.getDescription());
    }
}
