package com.wang.opengl_10;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NavigationActivity extends Activity {

    ListView listView;

    List<Item> data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.navigation_activity);
        listView = findViewById(R.id.listview);

        addButton();
    }

    private void addButton() {
        data = new ArrayList<>();
        for (int i=2;i<=28;i++){
            Item item =new Item();
            item.description = items[i];
            item.activityClass ="com.android.chapter"+i;
          data.add(item);
        }
    }

   String [] items = {//GBK  or UTF-8
           "0",//
           "1",//
           "2 背景",//2
           "3 基本图形绘制",//3
           "4 3D图形绘制及透视",//4
           "5 光效",//5
           "6 材质",//6
           "7 纹理及纹理映射",//7
           "8 隧道实例",//8
           "9 雾气",//9
           "10 2D文字显示",//10
           "11 飘动的旗帜",//11
           "12 蒙版系统",//12
           "13 粒子系统",//13
           "14 变形",//14
           "15 多级纹理及二次几何体",//15
           "16 曲面映射",//16
           "17 多重纹理",//17
           "18 反射（蒙版缓存）",//18
           "19 图像文字",//19
           "20 反走样",//20
           "21 缓存及片源测试",//21
           "22 贝塞尔函数",//22
           "23 BLT函数",//23
           "24 TGA文件",//24
           "25 多重视口",//25
           "26 轨迹球",//26
           "27 射线拾取",//27
           "28 地形",//28
           "29 天空盒",//29
           "30 帧动画之MD2模型",//30
           "31 骨骼动画之MS3D模型",//31章节
           "32 碰撞检测" //32章节
   };


    @Override
    protected void onStart() {
        super.onStart();
        listView.setAdapter(new MyAdapter(data));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item item=data.get(position);

                    ComponentName componentName =new  ComponentName(NavigationActivity.this,item.activityClass+".ChapterMain");

                    Intent intent = new Intent();
                    intent.setComponent(componentName);
                    startActivity(intent);


            }
        });
    }



    class Item{
        String description;
       String activityClass;
    }


    class MyAdapter extends BaseAdapter {

         List<Item> items;
        public  MyAdapter(List<Item> items){
            this.items = items;

        }

        @Override
        public int getCount() {
            return items==null?0:items.size();
        }

        @Override
        public Item getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
             TextView button;
            if (convertView == null){


                button =  new TextView(NavigationActivity.this);
                ViewGroup.LayoutParams l = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                button.setGravity(Gravity.CENTER);
                button.setLayoutParams(l);

            }else {
                button = (TextView) convertView;
            }

            button.setText(items.get(position).description);
            return button;
        }
    }
}
