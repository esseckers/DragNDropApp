package com.esseckers.dragndropapp.view.adapter;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.esseckers.dragndropapp.R;
import com.esseckers.dragndropapp.linkedlist.Actor;
import com.esseckers.dragndropapp.linkedlist.ActorsLinkedList;
import com.esseckers.dragndropapp.utils.Utils;
import com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemConstants;
import com.h6ah4i.android.widget.advrecyclerview.draggable.ItemDraggableRange;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractDraggableItemViewHolder;

/**
 * Created by Ivan Danilov on 11.04.2016.
 * Email: esseckers@gmail.com
 */
public class DraggableAdapter extends RecyclerView.Adapter<DraggableAdapter.Holder>
        implements DraggableItemAdapter<DraggableAdapter.Holder> {
    private ActorsLinkedList data = new ActorsLinkedList();

    public DraggableAdapter() {
        setHasStableIds(true);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate((viewType == 0) ?
                R.layout.draggable_list_item : R.layout.draggable_list_item2, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        final Actor data = this.data.get(position);
        holder.mTextView1.setText(data.getName());
        final int dragState = holder.getDragStateFlags();
        if (((dragState & Draggable.STATE_FLAG_IS_UPDATED) != 0)) {
            int bgResId;
            if ((dragState & Draggable.STATE_FLAG_IS_ACTIVE) != 0) {
                bgResId = R.drawable.bg_item_dragging_active_state;
                Utils.clearState(holder.mContainer.getForeground());
            } else {
                bgResId = (dragState & Draggable.STATE_FLAG_DRAGGING) != 0
                        ? R.drawable.bg_item_dragging_state
                        : R.drawable.bg_item_normal_state;
            }
            holder.mContainer.setBackgroundResource(bgResId);
        }
    }

    @Override
    public int getItemCount() {
        return data.getSize();
    }

    @Override
    public void onMoveItem(int fromPosition, int toPosition) {
        if (fromPosition != toPosition) {
            data.sort(fromPosition, toPosition);
            notifyItemMoved(fromPosition, toPosition);
        }
    }

    @Override
    public boolean onCheckCanStartDrag(Holder holder, int position, int x, int y) {
        View containerView = holder.mContainer;
        return Utils.hitTest(holder.mDragHandle, x - containerView.getLeft() +
                        (int) (ViewCompat.getTranslationX(containerView) + 0.5f),
                y - containerView.getTop() +
                        (int) (ViewCompat.getTranslationY(containerView) + 0.5f));
    }

    @Override
    public ItemDraggableRange onGetItemDraggableRange(Holder holder, int position) {
        // no drag-sortable range specified
        return null;
    }

    public ActorsLinkedList getData() {
        return data;
    }

    public void setData(ActorsLinkedList data) {
        this.data = data;
        this.notifyDataSetChanged();
    }

    private interface Draggable extends DraggableItemConstants {
    }

    public static class Holder extends AbstractDraggableItemViewHolder {
        public FrameLayout mContainer;
        public View mDragHandle;
        public TextView mTextView1;

        public Holder(View v) {
            super(v);
            mContainer = (FrameLayout) v.findViewById(R.id.container);
            mDragHandle = v.findViewById(R.id.drag_handle);
            mTextView1 = (TextView) v.findViewById(android.R.id.text1);
        }
    }
}
