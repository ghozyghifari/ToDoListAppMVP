package pens.lab.app.belajaractivity.modul.edittask;

import android.view.View;

import pens.lab.app.belajaractivity.base.BaseFragmentHolderActivity;
import pens.lab.app.belajaractivity.data.model.Task;


public class EditTaskActivity extends BaseFragmentHolderActivity {
    EditTaskFragment editTaskFragment;
    private final int UPDATE_REQUEST = 2019;

    @Override
    protected void initializeFragment() {
        initializeView();

        btBack.setVisibility(View.GONE);
        btOptionMenu.setVisibility(View.GONE);
//        ivIcon.setImageResource(R.drawable.....);
        ivIcon.setVisibility(View.VISIBLE);

        editTaskFragment = new EditTaskFragment();
        Task task = getIntent().getExtras().getParcelable("taskData");
        int position = getIntent().getExtras().getInt("position");
        editTaskFragment.setTask(task);
        editTaskFragment.setPosition(position);
        setCurrentFragment(editTaskFragment, false);

    }




}
