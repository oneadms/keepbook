package com.keepbook.app.view.fragment.record;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.keepbook.app.R;
import com.keepbook.app.model.dto.KeepBookDTO;
import com.keepbook.app.util.BookUtils;
import com.keepbook.app.util.SqlLiteUtils;
import com.keepbook.app.view.fragment.base.BaseFragment;
import com.keepbook.app.viewmodel.DataModel;
import com.keepbook.app.wdiget.MyViewPager;
import com.xuexiang.xui.adapter.FragmentAdapter;
import com.xuexiang.xui.widget.dialog.materialdialog.DialogAction;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;
import com.xuexiang.xui.widget.picker.widget.builder.TimePickerBuilder;
import com.xuexiang.xui.widget.picker.widget.listener.OnTimeSelectListener;
import com.xuexiang.xui.widget.tabbar.TabControlView;
import com.xuexiang.xui.widget.toast.XToast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
    private LinearLayout btnDateSelect;
    private Date time;
    private DataModel dataModel;
    private String money;

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
        dataModel=new ViewModelProvider(this).get(DataModel.class);
        tabControlView = (TabControlView) view.findViewById(R.id.tab_control_view);
        recordViewPager = ((MyViewPager) view.findViewById(R.id.record_view_pager));
        fabRecord = (FloatingActionButton) view.findViewById(R.id.fab_record);

        editRemark = (MaterialEditText) view.findViewById(R.id.edit_remark);
        editMoney = (MaterialEditText) view.findViewById(R.id.edit_money);
        btnDateSelect = (LinearLayout)view.findViewById(R.id.btn_date_select);
        btnDateSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelected(Date date, View v) {
                        time = date;
                    }
                }).build().show();
            }
        });
        recordViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                try {
                    MODE = position;
                    dataModel.setPos(-1);
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
            MODE = PAY_MODE;
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
                        dataModel.setPos(-1);
                        recordViewPager.setCurrentItem(0);
                        XToast.success(getContext(), "支出").show();
                        MODE = PAY_MODE;
                        break;
//                        收入
                    case "IN":
                        dataModel.setPos(-1);
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
                money = editMoney.getText().toString();
                String remark = editRemark.getText().toString();
                if (TextUtils.isEmpty(money)) {
                    XToast.error(getContext(), "请选择金额").show();return;
                } else if (time == null) {

                    XToast.error(getContext(), "请选择日期").show();
                    return;
                } else if (TextUtils.isEmpty(category)) {
                    XToast.error(getContext(), "请选择类别").show();return;
                }


                new MaterialDialog.Builder(getContext())
                        .content("类别:" + category + "\n备注:" + remark + "\n金额:" + money + "\n时间:"+new SimpleDateFormat("yyyy-MM-dd", Locale.CHINESE).format(time) +"\n是否确认")
                        .positiveText("确认记录")
                        .negativeText("取消")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {


                                BookUtils bookUtils = new BookUtils(SqlLiteUtils.getInstance(getContext()));
                                if (PAY_MODE.equals(MODE)) {
                                    money = String.valueOf(-1.0 * Double.parseDouble(money));
                                }
                                Long rows = bookUtils.writeDataOfOne(new KeepBookDTO(category, Double.parseDouble(money), time, remark));
                                if (rows > 0) {

                                    XToast.success(getContext(), "记录成功").show();
                                    new ViewModelProvider(requireActivity()).get(DataModel.class).setData(bookUtils.data2BillVO(bookUtils.readData()));

                                } else {
                                    XToast.error(getContext(), "记录失败").show();
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
