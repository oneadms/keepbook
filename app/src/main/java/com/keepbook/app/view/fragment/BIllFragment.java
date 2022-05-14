package com.keepbook.app.view.fragment;

import android.graphics.Color;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.keepbook.app.R;
import com.keepbook.app.model.dto.KeepBookDTO;
import com.keepbook.app.view.fragment.base.BaseFragment;
import com.keepbook.app.viewholder.BillListViewHolder;
import com.keepbook.app.wdiget.MyListView;
import com.xuexiang.xui.adapter.listview.XListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 账单Fragment
 */
public class BIllFragment extends BaseFragment {
    private PieChart pieChart;
    private MyListView billListview;

    public BIllFragment() {
        super(R.layout.fragment_bill);
    }

    @Override
    protected void init() {
        initChart();
        billListview.setAdapter(new XListAdapter<KeepBookDTO>(getContext(), initData()) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                BillListViewHolder viewHolder;
                KeepBookDTO item = getItem(position);
                if (convertView == null) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_bill_list, parent, false);
                    viewHolder = new BillListViewHolder();
                    viewHolder.tvTime = (TextView) convertView.findViewById(R.id.tv_time);
                    viewHolder.tvPay = (TextView) convertView.findViewById(R.id.tv_pay);
                    viewHolder.tvCome = (TextView) convertView.findViewById(R.id.tv_come);
                    viewHolder.tvBalance = (TextView) convertView.findViewById(R.id.tv_balance);
                    convertView.setTag(viewHolder);
                }else{
                    viewHolder = (BillListViewHolder) convertView.getTag();
                }
                viewHolder.tvTime.setText("五月");
                viewHolder.tvCome.setText(item.getMoney()+"");
                viewHolder.tvPay.setText(item.getMoney()+"");
                viewHolder.tvBalance.setText(item.getMoney()+"");
                return convertView;
            }
        });
    }

    private List<KeepBookDTO> initData() {
        ArrayList<KeepBookDTO> keepBookDTOS = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            keepBookDTOS.add(new KeepBookDTO("打飞机", 10.0, "打飞机"));
        }

        return keepBookDTOS;
    }

    private void initChart() {
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(70f, "支出"));
        pieEntries.add(new PieEntry(30f, "收入"));

        PieDataSet pieDataSet = new PieDataSet(pieEntries, "分类");
        pieDataSet.setColors(Color.parseColor("#ff1744"), Color.parseColor("#00e676"));
        pieDataSet.setDrawValues(true);
        pieDataSet.setValueTextColor(Color.parseColor("#ebeee8"));
        pieDataSet.setValueTextSize(dp2px(5));

        PieData pieData = new PieData(pieDataSet);

        pieChart.setDescription(null);
        pieChart.setCenterText("收入:支出");
        pieChart.setData(pieData);
    }

    private float dp2px(int dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    @Override
    protected void initView(View view) {

        pieChart = (PieChart) view.findViewById(R.id.pie_chart);
        billListview = (MyListView) view.findViewById(R.id.bill_listview);
    }

    @Override
    public void onClick(View view) {

    }
}
