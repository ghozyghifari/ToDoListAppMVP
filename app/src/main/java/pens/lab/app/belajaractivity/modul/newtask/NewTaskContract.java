package pens.lab.app.belajaractivity.modul.newtask;

import pens.lab.app.belajaractivity.base.BasePresenter;
import pens.lab.app.belajaractivity.base.BaseView;
import pens.lab.app.belajaractivity.data.model.Task;

/**
 * Created by fahrul on 13/03/19.
 */

public interface NewTaskContract {
    interface View extends BaseView<Presenter> {
        void redirectToTaskList(Task task);
    }

    interface Presenter extends BasePresenter {
        void saveData(String title, String description);
    }
}
