package com.esseckers.dragndropapp.view.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.esseckers.dragndropapp.R;
import com.esseckers.dragndropapp.controller.DataManager;
import com.esseckers.dragndropapp.linkedlist.Actor;
import com.esseckers.dragndropapp.linkedlist.ActorsLinkedList;
import com.esseckers.dragndropapp.view.adapter.DraggableAdapter;
import com.esseckers.dragndropapp.view.event.DragOnDropListener;
import com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager;

import butterknife.Bind;

/**
 * Created by Ivan Danilov on 11.04.2016.
 * Email: esseckers@gmail.com
 */
public class MainActivity extends AbstractActivity {

    @Bind(R.id.rv_actors)
    RecyclerView rvActors;

    private DraggableAdapter adapter;

    @Override
    protected void initView() {
        DataManager.fillDatabaseIfEmpty(getContentResolver());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        RecyclerViewDragDropManager recyclerViewDragDropManager = new RecyclerViewDragDropManager();
        recyclerViewDragDropManager.setInitiateOnLongPress(true);
        recyclerViewDragDropManager.setInitiateOnMove(false);
        adapter = new DraggableAdapter();
        RecyclerView.Adapter wrappedAdapter = recyclerViewDragDropManager.createWrappedAdapter(adapter);

        rvActors.setLayoutManager(layoutManager);
        rvActors.setAdapter(wrappedAdapter);

        recyclerViewDragDropManager.attachRecyclerView(rvActors);
        recyclerViewDragDropManager.setOnItemDragEventListener(new DragOnDropListener() {
            @Override
            public void onItemDragFinished(int fromPosition, int toPosition, boolean result) {
                if (fromPosition != toPosition) {
                    ActorsLinkedList linkedList = adapter.getData();
                    Actor actor1 = linkedList.get(fromPosition);
                    actor1.setId(fromPosition);
                    Actor actor2 = linkedList.get(toPosition);
                    actor2.setId(toPosition);
                    DataManager.sort(getContentResolver(), actor1, actor2);
                }
            }
        });
        adapter.setData(DataManager.getActors(getContentResolver()));
    }
}
