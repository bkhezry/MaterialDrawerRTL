package ir.bkhezry.materialdrawerrtl;
import android.graphics.Color;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.Drawer;

import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;
public class MainActivity extends AppCompatActivity {
    private AccountHeader headerResult = null;
    private Drawer result = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Handle Toolbar
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        // Create a few sample profile
        IProfile profile = new ProfileDrawerItem().withName("Behrouz Khezry").withEmail("bkhezry@gmail.com").withIcon(R.drawable.profile);
        // Create the AccountHeader
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withCompactStyle(true)
                .addProfiles(
                        profile, new ProfileSettingDrawerItem().withName("Add Account").withDescription("Add new Gmail Account").withIcon(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_plus).actionBar().paddingDp(5).colorRes(R.color.material_drawer_dark_primary_text)).withIdentifier(1),
                        new ProfileSettingDrawerItem().withName("Manage Account").withIcon(GoogleMaterial.Icon.gmd_settings)
                )
                .withSavedInstance(savedInstanceState)
                .withTextColor(Color.BLACK)
                .build();
        result = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Home").withIcon(FontAwesome.Icon.faw_home).withIdentifier(1),
                        new PrimaryDrawerItem().withName("Free Play").withIcon(FontAwesome.Icon.faw_gamepad),
                        new PrimaryDrawerItem().withName("Custom").withIcon(FontAwesome.Icon.faw_eye),
                        new SectionDrawerItem().withName("Section Header"),
                        new SecondaryDrawerItem().withName("Settings").withIcon(FontAwesome.Icon.faw_cog),
                        new SecondaryDrawerItem().withName("Help").withIcon(FontAwesome.Icon.faw_question).withEnabled(false),
                        new SecondaryDrawerItem().withName("Source").withIcon(FontAwesome.Icon.faw_github),
                        new SecondaryDrawerItem().withName("Contact").withIcon(FontAwesome.Icon.faw_bullhorn)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem != null && drawerItem.getIdentifier() == 1) {
                        }

                        if (drawerItem instanceof Nameable) {
                            //toolbar.setTitle(((Nameable) drawerItem).getName().getText(MainActivity.this));
                            toolbar.setTitle("");
                        }

                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .withDrawerGravity(Gravity.END)
                .build();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.drawershow) {
            result.openDrawer();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
