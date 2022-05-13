package com.keepbook.app.view.fragment.record;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.keepbook.app.R;
import com.keepbook.app.model.vo.IconTitleItem;
import com.keepbook.app.view.fragment.base.BaseFragment;
import com.keepbook.app.viewholder.IconItemViewHolder;
import com.xuexiang.xui.adapter.recyclerview.XRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 收入fragment
 */
public class ComeInFragment extends BaseFragment {

    private RecyclerView recCome;

    public ComeInFragment() {
        super(R.layout.fragment_come);
    }
    private Integer mPos=-1;
    @Override
    protected void init() {
        recCome.setLayoutManager(new GridLayoutManager(getContext(), 4));
        recCome.setAdapter(new XRecyclerAdapter<IconTitleItem, IconItemViewHolder>(initData()) {
            @NonNull
            @Override
            protected IconItemViewHolder getViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(getContext().getApplicationContext()).inflate(R.layout.item_icon_title, parent, false);
                return new IconItemViewHolder(view);
            }

            @Override
            protected void bindData(@NonNull IconItemViewHolder holder, int position, IconTitleItem item) {
                holder.icImage.setImageResource(item.getImageId());
                holder.icTv.setText(item.getTitle());
                holder.icImage.setTouchSelectModeEnabled(true);
                if (mPos == position) {
                    holder.icImage.setSelected(true);
                }else{
                    holder.icImage.setSelected(false);

                }
                holder.icImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPos = position;
                        notifyDataSetChanged();
                    }
                });
            }
        });
    }

    private List<IconTitleItem> initData() {
        List<IconTitleItem> data = new ArrayList<>();
        data.add(new IconTitleItem("工资", R.drawable.ic_earning_salary));
        data.add(new IconTitleItem("红包", R.drawable.ic_earning_red_envelope));
        data.add(new IconTitleItem("租金", R.drawable.ic_earning_rent));
        data.add(new IconTitleItem("礼金", R.drawable.ic_earning_gift_money));
        data.add(new IconTitleItem("分红", R.drawable.ic_earning_dividend));
        data.add(new IconTitleItem("理财", R.drawable.records_type_yundong));
        data.add(new IconTitleItem("年终奖", R.drawable.ic_earning_yearend_awards));





        return data;
    }

    @Override
    protected void initView(View view) {

        recCome = (RecyclerView) view.findViewById(R.id.rec_come);

    }

    @Override
    public void onClick(View view) {

    }

}
