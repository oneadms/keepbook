package com.keepbook.app.view.fragment;

import android.view.View;

import com.keepbook.app.R;
import com.keepbook.app.view.fragment.base.BaseFragment;
import com.xuexiang.xui.widget.tabbar.TabControlView;
import com.xuexiang.xui.widget.toast.XToast;

public class RecordFragment extends BaseFragment {


    private TabControlView tabControlView;

    public RecordFragment(int contentLayoutId) {
        super(contentLayoutId);
    }

    protected void initView(View view) {
        tabControlView = (TabControlView)view.findViewById(R.id.tab_control_view);
    }

    protected void init() {
        tabControlView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tab_control_view:
                String checked = tabControlView.getChecked();
                XToast.info(getContext(), "checked" + ":" + checked);
                break;
        }
    }
}
