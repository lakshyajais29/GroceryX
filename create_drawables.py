import os

res_dir = r"c:\Users\LAKSHYA JAISWAL\FlutterProjects\GroceryX\app\src\main\res\drawable"

drawables = [
    "ic_all.xml", "ic_fruits.xml", "ic_dairy.xml", "ic_snacks.xml", "ic_beverages.xml", "ic_bakery.xml", "ic_cleaning.xml",
    "img_banana.xml", "img_tomato.xml", "img_onion.xml", "img_spinach.xml", "img_milk.xml", "img_butter.xml", "img_eggs.xml",
    "img_lays.xml", "img_kurkure.xml", "img_hideseek.xml", "img_coke.xml", "img_tropicana.xml", "img_redbull.xml",
    "img_bread.xml", "img_croissant.xml", "img_muffin.xml", "img_surfexcel.xml", "img_vim.xml", "img_harpic.xml", "img_lizol.xml"
]

placeholder = '''<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">
    <solid android:color="#E0E0E0" />
    <corners android:radius="8dp" />
</shape>
'''

for d in drawables:
    with open(os.path.join(res_dir, d), "w") as f:
        f.write(placeholder)

print("Created placeholder drawables")
