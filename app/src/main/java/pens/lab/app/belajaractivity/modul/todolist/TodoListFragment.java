package pens.lab.app.belajaractivity.modul.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import pens.lab.app.belajaractivity.FirstActivity;
import pens.lab.app.belajaractivity.R;
import pens.lab.app.belajaractivity.base.BaseFragment;
import pens.lab.app.belajaractivity.data.model.Task;
import pens.lab.app.belajaractivity.modul.edittask.EditTaskActivity;
import pens.lab.app.belajaractivity.modul.newtask.NewTaskActivity;
import pens.lab.app.belajaractivity.utils.RecyclerViewAdapterTodolist;


/**
 * Created by fahrul on 13/03/19.
 */

public class TodoListFragment extends BaseFragment<TodoListActivity, TodoListContract.Presenter> implements TodoListContract.View {

    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    FloatingActionButton buttonAdd;

    public TodoListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_todolist, container, false);
        mPresenter = new TodoListPresenter(this);
        mPresenter.start();

        mRecyclerView = fragmentView.findViewById(R.id.recyclerViewTodolist);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(activity);
        mRecyclerView.setLayoutManager(mLayoutManager);
        final ArrayList<Task> data = mPresenter.getDataSet();
        mAdapter = new RecyclerViewAdapterTodolist(data);
        mRecyclerView.setAdapter(mAdapter);
        setTitle("Todo List");

        buttonAdd = fragmentView.findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoNewTask();
            }
        });

        ((RecyclerViewAdapterTodolist) mAdapter).setOnItemClickListener(new RecyclerViewAdapterTodolist.MyClickListener() {
            @Override
            public void onItemClickEdit(int position, View v) {
                editTask(mPresenter.getTask(position), position);
            }

            @Override
            public void onItemClickDelete(int position, View v) {
                mPresenter.deleteTask(position);
                mAdapter.notifyDataSetChanged();
            }
        });

        return fragmentView;
    }

    /*@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Task task = (Task) data.getExtras().getParcelable("task");
        Log.d("tesu", "onActivityResult: "+task.getTitle());
        mPresenter.saveData(task);
        mAdapter.notifyDataSetChanged();
    }*/

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 200) {
            Task task = (Task) data.getExtras().getParcelable("task");
//        Log.d("tesu", "onActivityResult: "+task.getTitle());
            mPresenter.saveData(task);
            mAdapter.notifyDataSetChanged();
        } else if (requestCode == 201){
            Task task = (Task) data.getExtras().getParcelable("task");
            int position = (int) data.getExtras().getInt("position");
            mPresenter.updateTask(position, task);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setPresenter(TodoListContract.Presenter presenter) {
        mPresenter = presenter;
    }


    @Override
    public void gotoNewTask() {
        Intent intent = new Intent(activity, NewTaskActivity.class);
        startActivityForResult(intent, 200);
    }

    public void editTask(Task task, int position) {
        Intent intent = new Intent(activity, EditTaskActivity.class);
        intent.putExtra("taskData", task);
        intent.putExtra("position", position);
        startActivityForResult(intent, 201);
    }



}
