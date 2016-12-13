package by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.asynctask;


import android.support.annotation.NonNull;

import java.util.List;

public interface UpdateData<T> {

    void endLoader(@NonNull List<T> data);
}
