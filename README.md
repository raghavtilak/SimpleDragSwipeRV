# SimpleDragSwipeAdapter
Simple RecyclerView Adapter with Drag-Sort and Swipe Delete functionality.
<br>
[!["Buy Me A Coffee"](https://www.buymeacoffee.com/assets/img/custom_images/orange_img.png)](https://www.buymeacoffee.com/raghavtilak)

[![Generic badge](https://img.shields.io/badge/jitpack-1.0.0-green.svg)](https://shields.io/)

### Functionality #
1. Swipe UP/DOWN (Horizontal) Swipe RIGHT/LEFT (Vertical) to remove an item.
2. LongPress and Drag to sort items in RecyclerView.

# Demo 📱 #


LinearLayout | HorizontalLayout | GridLayout  
---	     | ---              | --- 
|<img src="https://user-images.githubusercontent.com/74963954/126496700-42364828-915c-485b-8db9-fc80712ebfc6.gif" width="70%" />|<img src="https://user-images.githubusercontent.com/74963954/126499705-e57764f6-31ab-4612-8567-93be2debf9d3.gif" width="70%" />|<img src="https://user-images.githubusercontent.com/74963954/126499749-a59dd8a8-6318-40e3-a3a3-2dc21c39fce1.gif" width="70%" />


# Usage 🛠️ #
## Dependency #
> Step 1. Add the JitPack repository to your build file
```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

>Step 2. Add the dependency
```
	dependencies {
	        implementation 'com.github.raghavtilak:SimpleDragSwipeRV:1.0.0'
	}
```
## Model Class #
```
public class UserModel {
    private String name;
    private String phone;
    private String country;

    public UserModel(String name, String phone, String country) {
        this.name = name;
        this.phone = phone;
        this.country = country;
    }
    ....
}    
```
## RecyclerView Adapter #
```
public class RVAdapter extends DragSwipeAdapter<UserModel> {


    public RVAdapter(@NonNull Context context, List<UserModel> datas, int itemLayoutId, RecyclerView recyclerView, boolean isCanDrag, boolean isCanSwipe, int displayMode, int itemSpacing, OnItemClick onItemClickListener) {
        super(context, datas, itemLayoutId, recyclerView, isCanDrag, isCanSwipe, displayMode, itemSpacing, onItemClickListener);
    }

    @Override
    protected void bindView(UserModel item, DragSwipeAdapter.ViewHolder viewHolder) {
        if(item!=null){
            TextView name=(TextView)viewHolder.getView(R.id.textViewName);
            TextView phone=(TextView)viewHolder.getView(R.id.textViewPhone);
            TextView country=(TextView)viewHolder.getView(R.id.textViewCountry);
            name.setText(item.getName());
            phone.setText(item.getPhone());
            country.setText(item.getCountry());
        }
    }

    @Override
    public void onItemSwiped() {

    }

    @Override
    public void onItemMoved() {

    }
}
```
## OnItemClickListener #
```
public class MainActivity extends AppCompatActivity implements DragSwipeAdapter.OnItemClick<UserModel> {
	...
	
   @Override
    public void onClick(View view, int position, UserModel item) {
        ...
    }
    
        ...
}
```

## Attach Adapter to RecyclerView
```
...
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        userList=new ArrayList<>();
        loadData();
        listAdapter = new RVAdapter(this,userList,R.layout.item_layout,
                recyclerView,
                true,false,RVAdapter.VERTICAL,
                16,this);
    }
    ...
```
# Constructor #
```
    public DragSwipeAdapter(@NonNull Context context, List<T> datas, int itemLayoutId,
                            RecyclerView recyclerView,
                            boolean isCanDrag, boolean isCanSwipe, int displayMode,
                            int itemSpacing, OnItemClick onItemClickListener)
```
