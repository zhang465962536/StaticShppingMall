package com.example.shoppingmall.Type.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.shoppingmall.Base.BaseFragment;
import com.example.shoppingmall.R;

//分类 fragment
public class TypeFragment extends BaseFragment {
    private TextView textView;
    private String toolsList[] = new String[]{"潮流女装","食物生鲜","家用电器","品牌男装","手机数码","美妆护肤","母婴频道","家居家纺","运动户外","玩具乐器","珠宝饰品","音像制品","家具建材","旅游出行"};
    private TextView toolsTextViews[];
    private View views[];
    private LayoutInflater inflater;
    private ScrollView scrollView;
    private int scrllViewWidth = 0, scrollViewMiddle = 0;
    private ViewPager shop_pager;
    private int currentItem=0;
    private ShopAdapter shopAdapter;
    private static String username;

    @Override
    public View initView() {
        Log.e("TAG", "分类页面的fragment的UI被初始化了" );
        View view = View.inflate(getContext(), R.layout.category,null);
        scrollView=(ScrollView) view.findViewById(R.id.tools_scrlllview);
        return view;
    }

    public static TypeFragment newInstance(String user){

        TypeFragment typeFragment = new TypeFragment();
        username= user;
        return typeFragment;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e("TAG", "分类页面的fragment的数据被初始化了" );
        shopAdapter=new ShopAdapter(getFragmentManager());
        inflater=LayoutInflater.from(getActivity());
        showToolsView();
        initPager();
    }

    /**
     * 动态生成显示items中的textview
     */
    private void showToolsView() {

        LinearLayout toolsLayout= getActivity().findViewById(R.id.tools);
        toolsTextViews=new TextView[toolsList.length];
        views=new View[toolsList.length];

        View view= View.inflate(getActivity(),R.layout.item_b_top_nav_layout, null);
        for (int i = 0; i < toolsList.length; i++) {
            if(view != null){
                ViewGroup parentViewGroup = (ViewGroup) view.getParent();
                if (parentViewGroup != null ) {
                    parentViewGroup.removeView(view);
                }
            }
            view.setId(i);
            view.setOnClickListener(toolsItemListener);
            TextView textView= view.findViewById(R.id.tv);
            textView.setText(toolsList[i]);
            toolsLayout.addView(view);
            toolsTextViews[i]= textView;
            views[i]=view;
        }
        changeTextColor(0);
    }

    private View.OnClickListener toolsItemListener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            shop_pager.setCurrentItem(v.getId());
        }
    };

    /**
     * initPager<br/>
     * 初始化ViewPager控件相关内容
     */
    private void initPager() {
        shop_pager=(ViewPager)getActivity().findViewById(R.id.goods_pager);
        shop_pager.setAdapter(shopAdapter);
        shop_pager.setOnPageChangeListener(onPageChangeListener);
    }

    /**
     * OnPageChangeListener<br/>
     * 监听ViewPager选项卡变化事的事件
     */

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageSelected(int arg0) {
            if(shop_pager.getCurrentItem()!=arg0)shop_pager.setCurrentItem(arg0);
            if(currentItem!=arg0){
                changeTextColor(arg0);
                changeTextLocation(arg0);
            }
            currentItem=arg0;
        }
        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }
        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    };

    /**
     * ViewPager 加载选项卡
     * @author Administrator
     *
     */
    private class ShopAdapter extends FragmentPagerAdapter {
        public ShopAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int arg0) {
            Fragment fragment =new Fragment_pro_type();
            Bundle bundle=new Bundle();
            String str=toolsList[arg0];
            bundle.putString("typename",str);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return toolsList.length;
        }
    }


    /**
     * 改变textView的颜色
     * @param id
     */
    private void changeTextColor(int id) {
        for (int i = 0; i < toolsTextViews.length; i++) {
            if(i!=id){
                toolsTextViews[i].setBackgroundResource(android.R.color.transparent);
                toolsTextViews[i].setTextColor(0xff000000);
            }
        }
        toolsTextViews[id].setBackgroundResource(android.R.color.white);
        toolsTextViews[id].setTextColor(0xffff5d5e);
    }

    /**
     * 改变栏目位置
     *
     * @param clickPosition
     */
    private void changeTextLocation(int clickPosition) {

        int x = (views[clickPosition].getTop() - getScrollViewMiddle() + (getViewheight(views[clickPosition]) / 2));
        scrollView.smoothScrollTo(0, x);
    }

    /**
     * 返回scrollview的中间位置
     *
     * @return
     */
    private int getScrollViewMiddle() {
        if (scrollViewMiddle == 0)
            scrollViewMiddle = getScrollViewheight() / 2;
        return scrollViewMiddle;
    }

    /**
     * 返回ScrollView的宽度
     *
     * @return
     */
    private int getScrollViewheight() {
        if (scrllViewWidth == 0)
            scrllViewWidth = scrollView.getBottom() - scrollView.getTop();
        return scrllViewWidth;
    }

    /**
     * 返回view的宽度
     *
     * @param view
     * @return
     */
    private int getViewheight(View view) {
        return view.getBottom() - view.getTop();
    }
}
