package com.androidapp.classifiedjobs.joblisting.adapter;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidapp.classifiedjobs.R;
import com.androidapp.classifiedjobs.databinding.JobItemBinding;
import com.androidapp.classifiedjobs.databinding.JobItemRevisedBinding;
import com.androidapp.classifiedjobs.helper.Functions;
import com.androidapp.classifiedjobs.joblisting.model.Job;
import com.androidapp.classifiedjobs.joblisting.model.JobData;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by ishan on 12-12-2016.
 */

public class JobListAdapter extends RecyclerView.Adapter<JobListAdapter.MyViewHolder> {
    private final Activity mActivity;
    private List<Job> jobList;
    private JobItemRevisedBinding dataBind;

    public void setItems(List<Job> inputList) {
        jobList = inputList;
        notifyDataSetChanged();
    }

    public JobListAdapter(Activity mActivity, List<Job> jobList) {
        this.mActivity = mActivity;
        this.jobList = jobList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        dataBind = DataBindingUtil.inflate(LayoutInflater.from(mActivity), R.layout.job_item_revised, parent, false);
        View itemView = dataBind.getRoot();
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.jobTitle.setTypeface(Functions.getTF(mActivity));

        String jobTitle = jobList.get(position).JobTitle();
        String imageUrl = jobList.get(position).ImageName();

        if (jobTitle != null && !jobTitle.trim().equals(""))
            holder.jobTitle.setText(jobTitle);
        else
            holder.jobTitle.setVisibility(View.GONE);
        if (imageUrl != null && !imageUrl.trim().equals(""))
            Glide.with(mActivity).load(imageUrl).placeholder(R.drawable.image_filler).centerCrop().thumbnail(0.5f).into(holder.jobImg);
        else
            Glide.with(mActivity).load(R.drawable.img1).placeholder(R.drawable.image_filler).centerCrop().thumbnail(0.5f).into(holder.jobImg);
    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView jobImg;
        TextView jobTitle;

        public MyViewHolder(View itemView) {
            super(itemView);
            jobImg = dataBind.jobImg;
            jobTitle = dataBind.jobTitle;
            // viewJob=dataBind.viewJob;
        }
    }
}
