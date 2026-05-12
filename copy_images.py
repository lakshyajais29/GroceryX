import os
import shutil
import glob

artifact_dir = r"C:\Users\LAKSHYA JAISWAL\.gemini\antigravity\brain\970efdfa-188e-465d-a551-88b9acb88f9e"
drawable_dir = r"c:\Users\LAKSHYA JAISWAL\FlutterProjects\GroceryX\app\src\main\res\drawable"

# Move and rename images
png_files = glob.glob(os.path.join(artifact_dir, "img_*.png"))
for png in png_files:
    basename = os.path.basename(png)
    # basename is like img_banana_1778582907107.png
    name_parts = basename.split("_")
    new_name = name_parts[0] + "_" + name_parts[1] + ".png" # img_banana.png
    dest = os.path.join(drawable_dir, new_name)
    shutil.copy2(png, dest)
    print(f"Copied {basename} to {new_name}")
    
    # Delete the corresponding xml placeholder
    xml_placeholder = os.path.join(drawable_dir, name_parts[0] + "_" + name_parts[1] + ".xml")
    if os.path.exists(xml_placeholder):
        os.remove(xml_placeholder)
        print(f"Removed {xml_placeholder}")

# Vector Drawables for categories
vectors = {
    "ic_all.xml": '''<vector xmlns:android="http://schemas.android.com/apk/res/android" android:width="24dp" android:height="24dp" android:viewportWidth="24" android:viewportHeight="24" android:tint="#1565C0">
  <path android:fillColor="@android:color/white" android:pathData="M4,4h6v6H4V4z M14,4h6v6h-6V4z M4,14h6v6H4V14z M14,14h6v6h-6V14z"/>
</vector>''',
    "ic_fruits.xml": '''<vector xmlns:android="http://schemas.android.com/apk/res/android" android:width="24dp" android:height="24dp" android:viewportWidth="24" android:viewportHeight="24" android:tint="#F44336">
  <path android:fillColor="@android:color/white" android:pathData="M12,2C6.48,2 2,6.48 2,12s4.48,10 10,10 10,-4.48 10,-10S17.52,2 12,2zM12,18c-3.31,0 -6,-2.69 -6,-6s2.69,-6 6,-6 6,2.69 6,6 -2.69,6 -6,6z"/>
</vector>''',
    "ic_dairy.xml": '''<vector xmlns:android="http://schemas.android.com/apk/res/android" android:width="24dp" android:height="24dp" android:viewportWidth="24" android:viewportHeight="24" android:tint="#2196F3">
  <path android:fillColor="@android:color/white" android:pathData="M19,3H5c-1.1,0 -2,0.9 -2,2v14c0,1.1 0.9,2 2,2h14c1.1,0 2,-0.9 2,-2V5c0,-1.1 -0.9,-2 -2,-2zM19,19H5V5h14v14zM7,10h10v2H7V10zM7,14h10v2H7V14z"/>
</vector>''',
    "ic_snacks.xml": '''<vector xmlns:android="http://schemas.android.com/apk/res/android" android:width="24dp" android:height="24dp" android:viewportWidth="24" android:viewportHeight="24" android:tint="#FF9800">
  <path android:fillColor="@android:color/white" android:pathData="M21,11h-3V7h3V11zM17,11h-3V7h3V11zM13,11H7V7h6V11zM21,17h-3v-4h3V17zM17,17h-3v-4h3V17zM13,17H7v-4h6V17zM5,21V3H3v18H5zM23,21V3h-2v18H23z"/>
</vector>''',
    "ic_beverages.xml": '''<vector xmlns:android="http://schemas.android.com/apk/res/android" android:width="24dp" android:height="24dp" android:viewportWidth="24" android:viewportHeight="24" android:tint="#795548">
  <path android:fillColor="@android:color/white" android:pathData="M4,19h16v2H4V19zM20,3H4v10c0,2.21 1.79,4 4,4h6c2.21,0 4,-1.79 4,-4v-3h2c1.11,0 2,-0.9 2,-2V5C22,3.89 21.11,3 20,3zM14,13c0,1.1 -0.9,2 -2,2H8c-1.1,0 -2,-0.9 -2,-2V5h8V13zM20,8h-2V5h2V8z"/>
</vector>''',
    "ic_bakery.xml": '''<vector xmlns:android="http://schemas.android.com/apk/res/android" android:width="24dp" android:height="24dp" android:viewportWidth="24" android:viewportHeight="24" android:tint="#FFC107">
  <path android:fillColor="@android:color/white" android:pathData="M12,2L4,5v6c0,5.55 3.84,10.74 9,12c5.16,-1.26 9,-6.45 9,-12V5L12,2zM12,10.99h7c-0.53,4.12 -3.28,7.79 -7,8.94V10.99zM12,4.14l5.4,2.02v4.83h-5.4V4.14z"/>
</vector>''',
    "ic_cleaning.xml": '''<vector xmlns:android="http://schemas.android.com/apk/res/android" android:width="24dp" android:height="24dp" android:viewportWidth="24" android:viewportHeight="24" android:tint="#00BCD4">
  <path android:fillColor="@android:color/white" android:pathData="M19,3H5c-1.1,0 -2,0.9 -2,2v14c0,1.1 0.9,2 2,2h14c1.1,0 2,-0.9 2,-2V5c0,-1.1 -0.9,-2 -2,-2zM19,19H5V5h14v14zM12,17c-2.76,0 -5,-2.24 -5,-5s2.24,-5 5,-5 5,2.24 5,5 -2.24,5 -5,5zM12,9c-1.66,0 -3,1.34 -3,3s1.34,3 3,3 3,-1.34 3,-3 -1.34,-3 -3,-3z"/>
</vector>'''
}

for name, content in vectors.items():
    with open(os.path.join(drawable_dir, name), "w") as f:
        f.write(content)
        print(f"Created {name}")
