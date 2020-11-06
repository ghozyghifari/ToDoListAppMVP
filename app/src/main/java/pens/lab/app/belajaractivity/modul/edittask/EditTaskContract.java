package pens.lab.app.belajaractivity.modul.edittask;

import pens.lab.app.belajaractivity.base.BasePresenter;
import pens.lab.app.belajaractivity.base.BaseView;
import pens.lab.app.belajaractivity.data.model.Task;

/**
 * Created by fahrul on 13/03/19.
 */

public interface EditTaskContract {
    interface View extends BaseView<Presenter> {
        void redirectToTaskList(Task task);
        void showData(Task task);
        void setId(String id);
        void setTask(Task task);
        void setPosition(int position);
    }

    interface Presenter extends BasePresenter {
        void saveData(String title, String description);
        void loadData(Task task);
    }
}
