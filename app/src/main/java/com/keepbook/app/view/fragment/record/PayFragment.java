package com.keepbook.app.view.fragment.record;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.keepbook.app.R;
import com.keepbook.app.model.vo.IconTitleItem;
import com.keepbook.app.view.MainActivity;
import com.keepbook.app.view.fragment.base.BaseFragment;
import com.keepbook.app.viewholder.IconItemViewHolder;
import com.xuexiang.xui.adapter.recyclerview.XRecyclerAdapter;
import com.xuexiang.xui.widget.imageview.RadiusImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * 支出Fragment
 */
public class PayFragment extends BaseFragment {
    private RecyclerView recPay;
    private LinearLayout circleContainer;

    public PayFragment() {
        super(R.layout.fragment_pay);
    }

    private Integer mPos = -1;
    private float startX = 0;
    private float endX = 0;
    static int page = 1;

    @Override
    protected void init() {
        recPay.setLayoutManager(new GridLayoutManager(getContext(), 4, LinearLayoutManager.VERTICAL, false));

        List<IconTitleItem> iconTitleItems = initData();

        recPay.setAdapter(new XRecyclerAdapter<IconTitleItem, IconItemViewHolder>(iconTitleItems) {


            @NonNull
            @Override
            protected IconItemViewHolder getViewHolder(@NonNull ViewGroup parent, int viewType) {

                parent.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {

                        switch (event.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                startX = event.getRawX();
                                break;

                            case MotionEvent.ACTION_MOVE:
                                float MOVE_X = event.getRawX();
                                parent.setTranslationX(MOVE_X - startX);
                                break;
                            case MotionEvent.ACTION_UP:
                                parent.setTranslationX(0);
                                endX = event.getRawX();
                                Log.i("TAG", "startX-endX=" + (startX - endX));
                                if (startX - endX > 100) {

                                    page++;
                                    if (page > maxPage()) {
                                        page = 1;
                                    }
                                    notifyCirclerChanged(page);

                                    refresh(iconTitleItems.subList((page - 1) * 12, (page - 1) * 12 + 12));
                                    mPos = -1;
                                }else{
//                                    滑动条件不满足 继续分发
                                    return false;
                                }

                                break;
                        }
                        return true;
                    }
                });
                View view = LayoutInflater.from(getContext().getApplicationContext()).inflate(R.layout.item_icon_title, parent, false);
                return new IconItemViewHolder(view);
            }

            public int maxPage() {
                return iconTitleItems.size() / 12;
            }

            @Override
            public int getItemCount() {
                return 12;
            }

            @Override
            protected void bindData(@NonNull IconItemViewHolder holder, int position, IconTitleItem item) {
                holder.icImage.setImageResource(item.getImageId());
                holder.icTv.setText(item.getTitle());
                holder.icImage.setTouchSelectModeEnabled(true);
                if (mPos == position) {
                    holder.icImage.setSelected(true);
                } else {
                    holder.icImage.setSelected(false);

                }
                holder.icImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPos = position;
                        Log.i("TAG", "getContext().getClass()=" + getContext().getClass());
                        Log.i("TAG", "PayFragment.this.getParentFragment()=" + PayFragment.this.getParentFragment());
                        Log.i("TAG", "PayFragment.this.getContext()=" + PayFragment.this.getContext());
                        ((MainActivity) getContext()).setCategory(item.getTitle());

                        notifyDataSetChanged();
                    }
                });
            }
        });
    }

    private void notifyCirclerChanged(int page) {
        int index=page-1;
        for (int i = 0; i < circleContainer.getChildCount(); i++) {

            ((RadiusImageView) circleContainer.getChildAt(i)).setImageResource(R.color.black);
        }
        ((RadiusImageView) circleContainer.getChildAt(index)).setImageResource(R.color.xui_config_color_blue);

    }

    private List<IconTitleItem> initData() {
        List<IconTitleItem> data = new ArrayList<>();
        data.add(new IconTitleItem("餐饮", R.drawable.records_type_canyin));
        data.add(new IconTitleItem("购物", R.drawable.records_type_gouwu));
        data.add(new IconTitleItem("日用", R.drawable.records_type_riyong));
        data.add(new IconTitleItem("交通", R.drawable.records_type_jiaotong));
        data.add(new IconTitleItem("蔬菜", R.drawable.records_type_shucai));
        data.add(new IconTitleItem("水果", R.drawable.records_type_shuiguo));
        data.add(new IconTitleItem("零食", R.drawable.records_type_lingshi));
        data.add(new IconTitleItem("运动", R.drawable.records_type_yundong));
        data.add(new IconTitleItem("娱乐", R.drawable.records_type_yule));
        data.add(new IconTitleItem("通讯", R.drawable.records_type_tongxun));
        data.add(new IconTitleItem("服饰", R.drawable.records_type_fushi));
        data.add(new IconTitleItem("美容", R.drawable.records_type_meirong));
        data.add(new IconTitleItem("住房", R.drawable.records_type_zhufang));
        data.add(new IconTitleItem("家庭", R.drawable.records_type_jiating));
        data.add(new IconTitleItem("社交", R.drawable.records_type_shejiao));
        data.add(new IconTitleItem("旅行", R.drawable.records_type_lvxing));
        data.add(new IconTitleItem("烟酒", R.drawable.records_type_yanjiu));
        data.add(new IconTitleItem("数码", R.drawable.records_type_shuma));
        data.add(new IconTitleItem("汽车", R.drawable.records_type_qiche));
        data.add(new IconTitleItem("医疗", R.drawable.records_type_yiliao));
        data.add(new IconTitleItem("书籍", R.drawable.records_type_shuji));
        data.add(new IconTitleItem("学习", R.drawable.records_type_xuexi));
        data.add(new IconTitleItem("宠物", R.drawable.records_type_chongwu));
        data.add(new IconTitleItem("礼金", R.drawable.records_type_lijin));
        data.add(new IconTitleItem("礼品", R.drawable.records_type_lipin));
        data.add(new IconTitleItem("办公", R.drawable.records_type_bangong));
        data.add(new IconTitleItem("维修", R.drawable.records_type_weixiu));
        data.add(new IconTitleItem("捐赠", R.drawable.records_type_juanzeng));
        data.add(new IconTitleItem("彩票", R.drawable.records_type_caipiao));
        data.add(new IconTitleItem("红包", R.drawable.records_type_hongbao));
        data.add(new IconTitleItem("快递", R.drawable.records_type_kuaidi));
        data.add(new IconTitleItem("其他", R.drawable.records_type_qita));
        data.add(new IconTitleItem("还款", R.drawable.records_type_huankuan));
        data.add(new IconTitleItem("借出", R.drawable.records_type_jiechu));
        data.add(new IconTitleItem("饮品", R.drawable.records_type_yiliao));
        data.add(new IconTitleItem("追星", R.drawable.records_type_lvxing));


        return data;
    }

    @Override
    protected void initView(View view) {

        recPay = (RecyclerView) view.findViewById(R.id.rec_pay);

        circleContainer = (LinearLayout) view.findViewById(R.id.circle_container);
    }

    @Override
    public void onClick(View view) {

    }


}
