package com.gob.proyectomontpedidosinicial.core;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.gob.proyectomontpedidosinicial.R;

import java.util.ArrayList;
import java.util.List;


public abstract class LoaderAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected boolean showLoader;
    private static final int VIEWTYPE_ITEM = 1;
    private static final int VIEWTYPE_LOADER = 2;

    private static final String TAG = "ELIMINAR";
    private static final String TAG2 = "AGREGAR";

    protected List<T> mItems;
    protected List<T> mItemsTwo;
    protected LayoutInflater mInflater;

    public List<T> getmItems() {
        return mItems;
    }

    public List<T> getmItemsTwo() {
        return mItemsTwo;
    }

    public LoaderAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == VIEWTYPE_LOADER) {

            // Your Loader XML view here
            /*View view = mInflater.inflate(R.layout.item_loader, viewGroup, false);*/
            View vi = mInflater.inflate(R.layout.item_carga_recycler,viewGroup,false);
            // Your LoaderViewHolder class
            return new LoaderViewHold(vi);

        } else if (viewType == VIEWTYPE_ITEM) {
                return getYourItemViewHolder(viewGroup);
        }
        throw new IllegalArgumentException("Invalid ViewType: " + viewType);
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int posicion) {

        if (viewHolder instanceof LoaderViewHold) {
            LoaderViewHold loaderViewHol = (LoaderViewHold) viewHolder;
            if (showLoader) {
                loaderViewHol.progresoIni.setVisibility(View.VISIBLE);
            } else {
                loaderViewHol.progresoIni.setVisibility(View.GONE);
            }
            return;
        }
        bindYourViewHolder(viewHolder, posicion);
    }


    @Override
    public int getItemCount() {

        // If no items are present, there's no need for loader
        if (mItems == null || mItems.size() == 0) {
            return 0;
        }
        // +1 for loader
        return mItems.size() + 1;
    }

    @Override
    public long getItemId(int position) {

        // loader can't be at position 0
        // loader can only be at the last position
        if (position != 0 && position == getItemCount() - 1) {
            // id of loader is considered as -1 here
            return -1;
        }
        return getYourItemId(position);
    }

    public void addItem(T item) {
        mItems.add(item);
        /*notifyDataSetChanged();*/
        notifyItemInserted(mItems.size());
    }

    public void addItemFirst(T item, int position) {
      /*  mItems.add(0,item);
        notifyDataSetChanged();*/
        String pos = String.valueOf(position);
        mItems.add(0, item);
        notifyItemInserted(position);
        notifyItemRangeChanged(position, mItems.size());
        Log.e(TAG2 , pos);
    }

    public void deleteItem(int position){
        /*  String pos = String.valueOf(position);*/
        mItems.remove(position);
        /*notifyItemRemoved(position);
        notifyItemRangeChanged(position, mItems.size());*/
        notifyDataSetChanged();
       /* notifyItemRemoved(position);
        notifyItemRangeChanged(position, mItems.size());*/
        /*Log.e(TAG , pos);*/
    }



    @Override
    public int getItemViewType(int position) {

       /* return  position;*/
        if (position != 0 && position == getItemCount() - 1) {
            return VIEWTYPE_LOADER;
        }
        return VIEWTYPE_ITEM;
    }


    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }


    public void showLoading(boolean status) {
        showLoader = status;
    }

    public void setItems(List<T> items) {
        mItems = items;
        mItemsTwo = new ArrayList<>(mItems);
        notifyDataSetChanged();
    }


    public abstract long getYourItemId(int position);
    public abstract RecyclerView.ViewHolder getYourItemViewHolder(ViewGroup parent);
    public abstract void bindYourViewHolder(RecyclerView.ViewHolder holder, int position);

}