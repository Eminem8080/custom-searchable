package br.com.customsearchable.adapter;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.customsearchable.R;
import br.com.customsearchable.model.CustomSearchableInfo;
import br.com.customsearchable.model.ResultItem;


/**
 * Created by edgar on 6/14/15.
 */
public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<br.com.customsearchable.model.ResultItem> dataSet;

    // Constructors ________________________________________________________________________________
    public SearchAdapter (List<br.com.customsearchable.model.ResultItem> dataSet) {
        this.dataSet = dataSet;
    }

    // Callbacks ___________________________________________________________________________________
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_details, parent, false);

        ViewHolder holder = new ViewHolder(view);

        // UI configuration
        android.view.ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = CustomSearchableInfo.getResultItemHeight();
        view.setLayoutParams(params);

        holder.header.setTextColor(CustomSearchableInfo.getTextPrimaryColor());
        holder.header.setTextSize(TypedValue.COMPLEX_UNIT_PX, CustomSearchableInfo.getResultItemHeaderTextSize());
        holder.header.setHintTextColor(CustomSearchableInfo.getTextHintColor());

        holder.subHeader.setTextColor(CustomSearchableInfo.getTextPrimaryColor());
        holder.subHeader.setTextSize(TypedValue.COMPLEX_UNIT_PX, CustomSearchableInfo.getResultItemSubheaderTextSize());
        holder.subHeader.setHintTextColor(CustomSearchableInfo.getTextHintColor());

        holder.leftIcon.setImageResource(CustomSearchableInfo.getSimpleSuggestionsLeftIcon());
        holder.rightIcon.setImageResource(CustomSearchableInfo.getSimpleSuggestionsRightIcon());

        // Change layout based on the user option of one or two-lines mode
        if (!CustomSearchableInfo.getIsTwoLineExhibition()) {
            holder.subHeader.setVisibility(TextView.GONE);
            holder.header.setTypeface(Typeface.DEFAULT);
            view.invalidate();
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;

        viewHolder.header.setText(dataSet.get(position).getHeader());
        viewHolder.subHeader.setText(dataSet.get(position).getSubHeader());

    }

    // Getters and Setters _________________________________________________________________________
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public ResultItem getItem (Integer position) {
        return dataSet.get(position);
    }

    // View Holder _________________________________________________________________________________
    private class ViewHolder extends RecyclerView.ViewHolder {

        public TextView header;
        public TextView subHeader;
        public ImageView leftIcon;
        public ImageView rightIcon;

        public ViewHolder (View v) {
            super (v);

            this.header = (TextView) v.findViewById(R.id.rd_header_text);
            this.subHeader = (TextView) v.findViewById(R.id.rd_sub_header_text);
            this.leftIcon = (ImageView) v.findViewById(R.id.rd_left_icon);
            this.rightIcon = (ImageView) v.findViewById(R.id.rd_right_icon);
        }
    }
}