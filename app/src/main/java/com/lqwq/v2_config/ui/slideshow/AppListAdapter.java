package com.lqwq.v2_config.ui.slideshow;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lqwq.v2_config.R;

import java.util.List;

public class AppListAdapter extends BaseQuickAdapter<AppList, BaseViewHolder> {
    public AppListAdapter(int layoutResId, @Nullable List<AppList> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, AppList item) {
        helper.setImageDrawable(R.id.imageView2,item.getDrawable())
                .setText(R.id.textappname,item.getAppName())
                .setText(R.id.textuid, item.getAppUID());
    }
}
