package com.keepbook.app.view.fragment.record;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.keepbook.app.R;
import com.keepbook.app.model.dto.KeepBookDTO;
import com.keepbook.app.util.BookUtils;
import com.keepbook.app.util.SqlLiteUtils;
import com.keepbook.app.view.fragment.base.BaseFragment;
import com.keepbook.app.wdiget.MyViewPager;
import com.xuexiang.xui.adapter.FragmentAdapter;
import com.xuexiang.xui.widget.dialog.materialdialog.DialogAction;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;
import com.xuexiang.xui.widget.tabbar.TabControlView;
import com.xuexiang.xui.widget.toast.XToast;

import java.util.ArrayList;
import java.util.List;

public class RecordFragment extends BaseFragment {


    private TabControlView tabControlView;
    private ViewPager recordViewPager;
    private FloatingActionButton fabRecord;
    private Integer MODE;
    /**
     * 支出模式
     */
    private final Integer PAY_MODE = 0;
    /**
     * 收入模式
     */
    private final Integer COME_MODE = 1;
    private MaterialEditText editRemark;
    private MaterialEditText editMoney;
    /**
     * 类别 列表选中项名字
     */
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        Log.i("TAG", "category=" + category);
        this.category = category;
    }

    public RecordFragment() {
        super(R.layout.fragment_record);

    }

    protected void initView(View view) {
        tabControlView = (TabControlView) view.findViewById(R.id.tab_control_view);
        recordViewPager = ((MyViewPager) view.findViewById(R.id.record_view_pager));
        fabRecord = (FloatingActionButton) view.findViewById(R.id.fab_record);

        editRemark = (MaterialEditText)view.findViewById(R.id.edit_remark);
        editMoney = (MaterialEditText) view.findViewById(R.id.edit_money);
        recordViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                try {
                    MODE = position;
                    tabControlView.setSelectionTitle(position == 0 ? "支出" : "收入");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        try {
            tabControlView.setDefaultSelection(0);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void init() {
        List<Fragment> fragments = initFragments();

        recordViewPager.setAdapter(new FragmentAdapter<Fragment>(getActivity().getSupportFragmentManager(), fragments));
        tabControlView.setOnTabSelectionChangedListener(new TabControlView.OnTabSelectionChangedListener() {
            @Override
            public void newSelection(String title, String value) {
                switch (value) {
//                        支出
                    case "OUT":

                        recordViewPager.setCurrentItem(0);
                        XToast.success(getContext(), "支出").show();
                        MODE = PAY_MODE;
                        break;
//                        收入
                    case "IN":
                        recordViewPager.setCurrentItem(1);
                        MODE = COME_MODE;
                        XToast.success(getContext(), "收入").show();
                        break;
                }
            }
        });
        fabRecord.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                String money = editMoney.getText().toString();
                String remark = editRemark.getText().toString();
                new MaterialDialog.Builder(getContext())
                        .content("类别:" + category + "\n备注:" + remark + "\n金额:" + money + "\n是否确认")
                        .positiveText("确认记录")
                        .negativeText("取消")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                BookUtils bookUtils = new BookUtils(SqlLiteUtils.getInstance(getContext()));
                                Long rows = bookUtils.writeDataOfOne(new KeepBookDTO(category, Double.parseDouble(money), remark));
                                if (rows > 0) {

                                    XToast.success(getContext(), "记录成功").show();
                                }else{
                                    XToast.error(getContext(),"记录失败").show();
                                }
                            }
                        })
                        .show();



            }
        });
    }

    /**
     * 初始化 收入和支出 片段
     *
     * @return
     */
    private List<Fragment> initFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new PayFragment());
        fragments.add(new ComeInFragment());
        return fragments;
    }

    @Override
    public void onClick(View view) {

    }
}
