package com.example.student1.animals;

import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private ListView list;

    private MyHelper sqHelper;

    private SimpleCursorAdapter adapter;
    private String selection = "";
    private String orderBy = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.list);

        sqHelper = new MyHelper(this);

        adapter = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_1, // стандартный рендерер 1 строчный
                null, // курсор нулевой и ничего не покажет
                new String[] {
                        AnimalsTable.COLUMN_ANIMAL // маппинг
                },
                new int[] {
                        android.R.id.text1 // маппинг
                },
                0
        );

        list.setAdapter(adapter);
        registerForContextMenu(list); // подписка на контекст меню
        upgradeCursor();

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.main_up:
                orderBy = AnimalsTable.COLUMN_ANIMAL + " asc"; // означает, что сортируем по этой колонке по возрастанию
                upgradeCursor();
                return true;

            case R.id.main_down:
                orderBy = AnimalsTable.COLUMN_ANIMAL + " desc"; // означает, что сортируем по этой колонке по убыванию - desending
                upgradeCursor();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //if(item.getItemId() == )
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;

        deleteAnimal(position);

        return super.onContextItemSelected(item);
    }

    private void deleteAnimal(int position) {
        Cursor cursor = adapter.getCursor();
        cursor.moveToPosition(position); // переместели курсор на позицию

        // position - это не айди в базе данных. Нам надо еще его получить:
        int databaseId = cursor.getInt(
            cursor.getColumnIndex(AnimalsTable.COLUMN_ID) // у курсора спршиваем номер колонки среди тех колонок, что отдались
        );

        sqHelper.getWritableDatabase().delete(
                AnimalsTable.TABLE_ANIMALS,
                AnimalsTable.COLUMN_ID + "=?", /// =? значит то. Что то, что удаляю я параметризую и что удаляю находится в следующем массиве
                new String[] {
                        Integer.toString(databaseId)
                }
        );

        upgradeCursor();
        // "delete from ... where ...; drop database ..."
        // "select id, name from users where userid > 12 and age > 45 order by age desc"
    }

    private void upgradeCursor() {

        // позволяет обновить курсор:
        Cursor cursor = sqHelper.getReadableDatabase().query(
                AnimalsTable.TABLE_ANIMALS,
                null,
                selection,
                null,
                null,
                null,
                orderBy
        );

        adapter.swapCursor(cursor);
    }
}
