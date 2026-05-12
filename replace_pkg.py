import os

search_dir = r"c:\Users\LAKSHYA JAISWAL\FlutterProjects\GroceryX\app\src\main"

for root, _, files in os.walk(search_dir):
    for file in files:
        if file.endswith(".kt") or file.endswith(".xml"):
            path = os.path.join(root, file)
            with open(path, "r", encoding="utf-8") as f:
                content = f.read()
            
            if "com.oceanx.grocery" in content:
                new_content = content.replace("com.oceanx.grocery", "com.example.groceryx")
                with open(path, "w", encoding="utf-8") as f:
                    f.write(new_content)
                print(f"Updated {path}")
