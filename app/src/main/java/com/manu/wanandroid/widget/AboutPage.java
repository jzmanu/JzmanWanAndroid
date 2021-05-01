package com.manu.wanandroid.widget;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ScreenUtils;
import com.manu.wanandroid.R;
import com.manu.wanandroid.utils.BitmapUtil;

import androidx.annotation.DrawableRes;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.widget.TextViewCompat;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

/**
 * The main class of this library with many predefined methods to add Elements for common items in
 * an About page. This class creates a {@link android.view.View} that can be passed as the root view
 * in {@link Fragment#onCreateView(LayoutInflater, ViewGroup, Bundle)} or passed to the {@link android.app.Activity#setContentView(View)}
 * in an activity's {@link android.app.Activity#)} (Bundle)} method
 * <p>
 * To create a custom item in the about page, pass an instance of {@link mehdi.sakout.aboutpage.Element}
 * to the {@link AboutPage#addItem(Element)} method.
 *
 * @see Element
 */
public class AboutPage {
    private final Context mContext;
    private final LayoutInflater mInflater;
    private final View mView;
    private CharSequence mDescription;
    private CharSequence mBottomDescription;
    private int mImage = 0;
    private int mBottomImage = 0;
    private boolean mIsRTL = false;
    private Typeface mCustomFont;
    private Boolean mEnableDarkMode = false;
    private int mBackgroundColor;
    private int mIconColor;
    private int mTextColor;
    private int mSeparatorColor;

    /**
     * The AboutPage requires a context to perform it's functions. Give it a context associated to an
     * Activity or a Fragment. To avoid memory leaks, don't pass a
     * {@link android.content.Context#getApplicationContext() Context.getApplicationContext()} here.
     *
     * @param context
     */
    public AboutPage(Context context) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mView = mInflater.inflate(R.layout.about_page, null);
        this.enableDarkMode(false);
    }

    /**
     * Provide a valid path to a font here to use another font for the text inside this AboutPage
     *
     * @param path
     * @return this AboutPage instance for builder pattern support
     */
    public AboutPage setCustomFont(String path) {
        //TODO: check if file exists
        mCustomFont = Typeface.createFromAsset(mContext.getAssets(), path);
        return this;
    }

    /**
     * Provide a typeface to use as custom font
     *
     * @param typeface
     * @return this AboutPage instance for builder pattern support
     */
    public AboutPage setCustomFont(Typeface typeface) {
        mCustomFont = typeface;
        return this;
    }

    /**
     * Provide a way to force dark mode or not
     *
     * @param enabled
     * @return this AboutPage instance for builder pattern support
     */
    public AboutPage enableDarkMode(Boolean enabled) {
        mEnableDarkMode = enabled;
        if (enabled) {
            mBackgroundColor = ContextCompat
                    .getColor(mContext, R.color.about_background_dark_color);
            mTextColor = ContextCompat
                    .getColor(mContext, R.color.about_text_dark_color);
            mSeparatorColor = ContextCompat
                    .getColor(mContext, R.color.about_separator_dark_color);
            mIconColor = AboutPageUtils.getThemeAccentColor(mContext);

        } else {
            mBackgroundColor = ContextCompat
                    .getColor(mContext, R.color.about_background_color);
            mTextColor = ContextCompat
                    .getColor(mContext, R.color.about_text_color);
            mSeparatorColor = ContextCompat
                    .getColor(mContext, R.color.about_separator_color);
            mIconColor = ContextCompat
                    .getColor(mContext, R.color.about_item_icon_color);
        }
        return this;
    }

    /**
     * Convenience method for {@link AboutPage#addEmail(java.lang.String, java.lang.String)} but with
     * a predefined title string
     *
     * @param email the email address to send to
     * @return this AboutPage instance for builder pattern support
     */
    public AboutPage addEmail(String email) {
        return addEmail(email, mContext.getString(R.string.about_contact_us));
    }

    public AboutPage addEmail(String email, String title) {
        return addEmail(email, title, R.drawable.about_icon_email);
    }

    /**
     * Add a predefined Element that opens the users default email client with a new email to the
     * email address passed as parameter
     *
     * @param email the email address to send to
     * @param title the title string to display on this item
     * @return this AboutPage instance for builder pattern support
     */
    public AboutPage addEmail(String email, String title, @DrawableRes int res) {
        Element emailElement = new Element();
        emailElement.setTitle(title);
        emailElement.setIconDrawable(res);
        emailElement.setIconTint(R.color.about_item_icon_color);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        emailElement.setIntent(intent);

        addItem(emailElement);
        return this;
    }

    /**
     * Convenience method for {@link AboutPage#addFacebook(java.lang.String, java.lang.String)} but with
     * a predefined title string
     *
     * @param id the facebook id to display
     * @return this AboutPage instance for builder pattern support
     */
    public AboutPage addFacebook(String id) {
        return addFacebook(id, mContext.getString(R.string.about_facebook));
    }

    /**
     * Add a predefined Element that the opens Facebook app with a deep link to the specified user id
     * If the Facebook application is not installed this will open a web page instead.
     *
     * @param id    the id of the Facebook user to display in the Facebook app
     * @param title the title to display on this item
     * @return this AboutPage instance for builder pattern support
     */
    public AboutPage addFacebook(String id, String title) {
        Element facebookElement = new Element();
        facebookElement.setTitle(title);
        facebookElement.setIconDrawable(R.drawable.about_icon_facebook);
        facebookElement.setIconTint(R.color.about_facebook_color);
        facebookElement.setValue(id);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);

        if (AboutPageUtils.isAppInstalled(mContext, "com.facebook.katana")) {
            intent.setPackage("com.facebook.katana");
            int versionCode = 0;
            try {
                versionCode = mContext.getPackageManager().getPackageInfo("com.facebook.katana", 0).versionCode;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }

            if (versionCode >= 3002850) {
                Uri uri = Uri.parse("fb://facewebmodal/f?href=" + "http://m.facebook.com/" + id);
                intent.setData(uri);
            } else {
                Uri uri = Uri.parse("fb://page/" + id);
                intent.setData(uri);
            }
        } else {
            intent.setData(Uri.parse("http://m.facebook.com/" + id));
        }

        facebookElement.setIntent(intent);

        addItem(facebookElement);
        return this;
    }

    /**
     * Convenience method for {@link AboutPage#addTwitter(String, String)} but with
     * a predefined title string
     *
     * @param id the Twitter id to display
     * @return this AboutPage instance for builder pattern support
     */
    public AboutPage addTwitter(String id) {
        return addTwitter(id, mContext.getString(R.string.about_twitter));
    }


    /**
     * Add a predefined Element that the opens the Twitter app with a deep link to the specified user id
     * If the Twitter application is not installed this will open a web page instead.
     *
     * @param id    the id of the Twitter user to display in the Twitter app
     * @param title the title to display on this item
     * @return this AboutPage instance for builder pattern support
     */
    public AboutPage addTwitter(String id, String title) {
        Element twitterElement = new Element();
        twitterElement.setTitle(title);
        twitterElement.setIconDrawable(R.drawable.about_icon_twitter);
        twitterElement.setIconTint(R.color.about_twitter_color);
        twitterElement.setValue(id);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);

        if (AboutPageUtils.isAppInstalled(mContext, "com.twitter.android")) {
            intent.setPackage("com.twitter.android");
            intent.setData(Uri.parse(String.format("twitter://user?screen_name=%s", id)));
        } else {
            intent.setData(Uri.parse(String.format("http://twitter.com/intent/user?screen_name=%s", id)));
        }

        twitterElement.setIntent(intent);
        addItem(twitterElement);
        return this;
    }

    /**
     * Convenience method for {@link AboutPage#addPlayStore(String, String)} but with
     * a predefined title string
     *
     * @param id the package id of the app to display
     * @return this AboutPage instance for builder pattern support
     */
    public AboutPage addPlayStore(String id) {
        return addPlayStore(id, mContext.getString(R.string.about_play_store));
    }

    /**
     * Add a predefined Element that the opens the PlayStore app with a deep link to the
     * specified app application id.
     *
     * @param id    the package id of the app to display
     * @param title the title to display on this item
     * @return this AboutPage instance for builder pattern support
     */
    public AboutPage addPlayStore(String id, String title) {
        Element playStoreElement = new Element();
        playStoreElement.setTitle(title);
        playStoreElement.setIconDrawable(R.drawable.about_icon_google_play);
        playStoreElement.setIconTint(R.color.about_play_store_color);
        playStoreElement.setValue(id);

        Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=" + id);
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        playStoreElement.setIntent(goToMarket);

        addItem(playStoreElement);
        return this;
    }

    /**
     * Convenience method for {@link AboutPage#addYoutube(String, String)} but with
     * a predefined title string
     *
     * @param id the id of the channel to deep link to
     * @return this AboutPage instance for builder pattern support
     */
    public AboutPage addYoutube(String id) {
        return addYoutube(id, mContext.getString(R.string.about_youtube));
    }

    /**
     * Add a predefined Element that the opens the Youtube app with a deep link to the
     * specified channel id.
     * <p>
     * If the Youtube app is not installed this will open the Youtube web page instead.
     *
     * @param id    the id of the channel to deep link to
     * @param title the title to display on this item
     * @return this AboutPage instance for builder pattern support
     */
    public AboutPage addYoutube(String id, String title) {
        Element youtubeElement = new Element();
        youtubeElement.setTitle(title);
        youtubeElement.setIconDrawable(R.drawable.about_icon_youtube);
        youtubeElement.setIconTint(R.color.about_youtube_color);
        youtubeElement.setValue(id);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(String.format("http://youtube.com/channel/%s", id)));

        if (AboutPageUtils.isAppInstalled(mContext, "com.google.android.youtube")) {
            intent.setPackage("com.google.android.youtube");
        }

        youtubeElement.setIntent(intent);
        addItem(youtubeElement);

        return this;
    }

    /**
     * Convenience method for {@link AboutPage#addInstagram(String, String)} (String, String)} but with
     * a predefined title string
     *
     * @param id the id of the instagram user to deep link to
     * @return this AboutPage instance for builder pattern support
     */
    public AboutPage addInstagram(String id) {
        return addInstagram(id, mContext.getString(R.string.about_instagram));
    }

    /**
     * Add a predefined Element that the opens the Instagram app with a deep link to the
     * specified user id.
     * <p>
     * If the Instagram app is not installed this will open the Intagram web page instead.
     *
     * @param id    the user id to deep link to
     * @param title the title to display on this item
     * @return this AboutPage instance for builder pattern support
     */
    public AboutPage addInstagram(String id, String title) {
        Element instagramElement = new Element();
        instagramElement.setTitle(title);
        instagramElement.setIconDrawable(R.drawable.about_icon_instagram);
        instagramElement.setIconTint(R.color.about_instagram_color);
        instagramElement.setValue(id);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://instagram.com/_u/" + id));

        if (AboutPageUtils.isAppInstalled(mContext, "com.instagram.android")) {
            intent.setPackage("com.instagram.android");
        }

        instagramElement.setIntent(intent);
        addItem(instagramElement);

        return this;
    }

    /**
     * Convenience method for {@link AboutPage#addGitHub(String, String)} but with
     * a predefined title string
     *
     * @param id the id of the GitHub user to display
     * @return this AboutPage instance for builder pattern support
     */
    public AboutPage addGitHub(String id) {
        return addGitHub(id, mContext.getString(R.string.about_github));
    }

    /**
     * Add a predefined Element that the opens the a browser and displays the specified GitHub
     * users profile page.
     *
     * @param id    the GitHub user to link to
     * @param title the title to display on this item
     * @return this AboutPage instance for builder pattern support
     */
    public AboutPage addGitHub(String id, String title) {
        Element gitHubElement = new Element();
        gitHubElement.setTitle(title);
        gitHubElement.setIconDrawable(R.drawable.about_icon_github);
        gitHubElement.setIconTint(R.color.about_github_color);
        gitHubElement.setValue(id);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse(String.format("https://github.com/%s", id)));

        gitHubElement.setIntent(intent);
        addItem(gitHubElement);

        return this;
    }

    /**
     * Convenience method for {@link AboutPage#addWebsite(String, String)} but with
     * a predefined title string
     *
     * @param url the URL to open in a browser
     * @return this AboutPage instance for builder pattern support
     */
    public AboutPage addWebsite(String url) {
        return addWebsite(url, mContext.getString(R.string.about_website));
    }

    public AboutPage addWebsite(String url, String title) {
        return addWebsite(url, title, R.drawable.about_icon_link);
    }

    /**
     * Add a predefined Element that the opens a browser and loads the specified URL
     *
     * @param url   the URL to open in a browser
     * @param title the title to display on this item
     * @return this AboutPage instance for builder pattern support
     */
    public AboutPage addWebsite(String url, String title, @DrawableRes int res) {
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "http://" + url;
        }
        Element websiteElement = new Element();
        websiteElement.setTitle(title);
        websiteElement.setIconDrawable(res);
        websiteElement.setIconTint(R.color.about_item_icon_color);
        websiteElement.setValue(url);

        Uri uri = Uri.parse(url);
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, uri);

        websiteElement.setIntent(browserIntent);
        addItem(websiteElement);

        return this;
    }

    /**
     * Add a custom {@link Element} to this AboutPage
     *
     * @param element
     * @return this AboutPage instance for builder pattern support
     * @see Element
     */
    public AboutPage addItem(Element element) {
        return addItem(element,true);
    }

    public AboutPage addItem(Element element, boolean isIn) {
        LinearLayout wrapper = mView.findViewById(R.id.about_providers);
        wrapper.addView(createItem(element,isIn));
        wrapper.addView(getSeparator(), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mContext.getResources().getDimensionPixelSize(R.dimen.about_separator_height)));
        return this;
    }

    /**
     * Set the header image to display in this AboutPage
     *
     * @param resource the resource id of the image to display
     * @return this AboutPage instance for builder pattern support
     */
    public AboutPage setImage(@DrawableRes int resource) {
        this.mImage = resource;
        return this;
    }

    public AboutPage setBottomImage(@DrawableRes int resource) {
        this.mBottomImage = resource;
        return this;
    }

    /**
     * Add a new group that will display a header in this AboutPage
     * <p>
     * A header will be displayed in the order it was added. For e.g:
     * <p>
     * <code>
     * new AboutPage(this)
     * .addItem(firstItem)
     * .addGroup("Header")
     * .addItem(secondItem)
     * .create();
     * </code>
     * <p>
     * Will display the following
     * [First item]
     * [Header]
     * [Second item]
     *
     * @param name the title for this group
     * @return this AboutPage instance for builder pattern support
     */
    public AboutPage addGroup(String name) {

        TextView textView = new TextView(mContext);
        textView.setText(name);
        TextViewCompat.setTextAppearance(textView, R.style.about_groupTextAppearance);
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        if (mCustomFont != null) {
            textView.setTypeface(mCustomFont);
        }

        int padding = mContext.getResources().getDimensionPixelSize(R.dimen.about_group_text_padding);
        textView.setPadding(padding, padding, padding, padding);


        if (mIsRTL) {
            textView.setGravity(Gravity.END | Gravity.CENTER_VERTICAL);
            textParams.gravity = Gravity.END | Gravity.CENTER_VERTICAL;
        } else {
            textView.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
            textParams.gravity = Gravity.START | Gravity.CENTER_VERTICAL;
        }
        textView.setLayoutParams(textParams);

        ((LinearLayout) mView.findViewById(R.id.about_providers)).addView(textView);
        return this;
    }

    /**
     * Turn on the RTL mode.
     *
     * @param value
     * @return this AboutPage instance for builder pattern support
     */
    public AboutPage isRTL(boolean value) {
        this.mIsRTL = value;
        return this;
    }

    public AboutPage setDescription(CharSequence description) {
        this.mDescription = description;
        return this;
    }

    public AboutPage setBottomDescription(CharSequence description) {
        this.mBottomDescription = description;
        return this;
    }

    /**
     * Create and inflate this AboutPage. After this method is called the AboutPage
     * cannot be customized any more.
     *
     * @return the inflated {@link View} of this AboutPage
     */
    public View create() {
        TextView description = mView.findViewById(R.id.description);
        ImageView image = mView.findViewById(R.id.image);

        TextView bottomDescription = mView.findViewById(R.id.bottomDescription);
        ImageView bottomImage = mView.findViewById(R.id.bottomImage);

        View subWrapper = mView.findViewById(R.id.sub_wrapper);
        View descriptionSeparator = mView.findViewById(R.id.description_separator);

        int size = ScreenUtils.getScreenWidth();
        if (mImage > 0) {
            Bitmap bitmap = BitmapUtil.INSTANCE.decodeSampleFromBitmap(mContext,mImage,size / 4, size / 4);
            image.setImageBitmap(bitmap);
            ViewGroup.LayoutParams params = new LinearLayout.LayoutParams(size / 4, size / 4);
            image.setLayoutParams(params);
        }

        if (mBottomImage > 0) {
            bottomImage.setImageResource(mBottomImage);
            ViewGroup.LayoutParams params = new LinearLayout.LayoutParams(
                    (int) (size * 0.6),
                    (int) (size * 0.4));
            bottomImage.setLayoutParams(params);
        }

        if (!TextUtils.isEmpty(mDescription)) {
            description.setText(mDescription);
        }

        if (!TextUtils.isEmpty(mBottomDescription)) {
            bottomDescription.setText(mBottomDescription);
        }

        description.setGravity(Gravity.CENTER);
        description.setTextColor(mTextColor);

        bottomDescription.setGravity(Gravity.CENTER);
        bottomDescription.setTextColor(mTextColor);

        if (mCustomFont != null) {
            description.setTypeface(mCustomFont);
        }

        subWrapper.setBackgroundColor(mBackgroundColor);
        descriptionSeparator.setBackgroundColor(mSeparatorColor);

        return mView;
    }

    private View createItem(final Element element) {
        return createItem(element,true);
    }

    private View createItem(final Element element,boolean isIn) {
//        LinearLayout wrapper = new LinearLayout(mContext);
        RelativeLayout wrapper = new RelativeLayout(mContext);
//        wrapper.setOrientation(LinearLayout.HORIZONTAL);
        wrapper.setClickable(true);

        if (element.getOnClickListener() != null) {
            wrapper.setOnClickListener(element.getOnClickListener());
        } else if (element.getIntent() != null) {
            wrapper.setOnClickListener(view -> {
                try {
                    mContext.startActivity(element.getIntent());
                } catch (Exception e) {
                }
            });

        }

        TypedValue outValue = new TypedValue();
        mContext.getTheme().resolveAttribute(R.attr.selectableItemBackground, outValue, true);
        wrapper.setBackgroundResource(outValue.resourceId);

        int padding = mContext.getResources().getDimensionPixelSize(R.dimen.about_text_padding);
        wrapper.setPadding(padding, padding, padding, padding);
        LinearLayout.LayoutParams wrapperParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        wrapper.setLayoutParams(wrapperParams);

        TextView textView = new TextView(mContext);
        TextViewCompat.setTextAppearance(textView, R.style.about_elementTextAppearance);
        RelativeLayout.LayoutParams textParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textParams.addRule(RelativeLayout.CENTER_VERTICAL);


        if (mCustomFont != null) {
            textView.setTypeface(mCustomFont);
        }

        ImageView iconView = null;

        if (element.getIconDrawable() != null) {
            iconView = new ImageView(mContext);
            iconView.setId(R.id.about_item_icon);
            int size = mContext.getResources().getDimensionPixelSize(R.dimen.about_icon_size);
            RelativeLayout.LayoutParams iconParams = new RelativeLayout.LayoutParams(size, size);
            iconParams.addRule(RelativeLayout.CENTER_VERTICAL);
            iconView.setLayoutParams(iconParams);
            int iconPadding = mContext.getResources().getDimensionPixelSize(R.dimen.about_icon_padding);
            iconView.setPadding(iconPadding, 0, iconPadding, 0);

            if (Build.VERSION.SDK_INT < 21) {
                Drawable drawable = VectorDrawableCompat.create(iconView.getResources(), element.getIconDrawable(), iconView.getContext().getTheme());
                iconView.setImageDrawable(drawable);
            } else {
                iconView.setImageResource(element.getIconDrawable());
            }

            Drawable wrappedDrawable = DrawableCompat.wrap(iconView.getDrawable());
            wrappedDrawable = wrappedDrawable.mutate();

            if (!element.getSkipTint()) {
                if (element.getAutoApplyIconTint()) {
                    if (mEnableDarkMode) {
                        if (element.getIconNightTint() != null) {
                            DrawableCompat.setTint(wrappedDrawable, ContextCompat.getColor(mContext, element.getIconNightTint()));
                        } else {
                            DrawableCompat.setTint(wrappedDrawable, mIconColor);
                        }
                    } else {
                        if (element.getIconTint() != null) {
                            DrawableCompat.setTint(wrappedDrawable, ContextCompat.getColor(mContext, element.getIconTint()));
                        } else {
                            DrawableCompat.setTint(wrappedDrawable, mIconColor);
                        }
                    }

                } else if (element.getIconTint() != null) {
                    DrawableCompat.setTint(wrappedDrawable, ContextCompat.getColor(mContext, element.getIconTint()));
                } else if (mEnableDarkMode) {
                    DrawableCompat.setTint(wrappedDrawable, mIconColor);
                }

            }

        } else {
            int iconPadding = mContext.getResources().getDimensionPixelSize(R.dimen.about_icon_padding);
            textView.setPadding(iconPadding, iconPadding, iconPadding, iconPadding);
        }

        textView.setText(element.getTitle());
        textView.setTextColor(mTextColor);
        textParams.addRule(RelativeLayout.END_OF,R.id.about_item_icon);
        textView.setLayoutParams(textParams);

        if (isIn){
            ImageView imageIn = new ImageView(mContext);
            int size = mContext.getResources().getDimensionPixelSize(R.dimen.about_icon_size);
            RelativeLayout.LayoutParams iconParams = new RelativeLayout.LayoutParams(size, size);
            iconParams.addRule(RelativeLayout.ALIGN_PARENT_END);
            imageIn.setLayoutParams(iconParams);
            int iconPadding = mContext.getResources().getDimensionPixelSize(R.dimen.about_icon_padding);
            imageIn.setPadding(iconPadding, 0, iconPadding, 0);
            if (Build.VERSION.SDK_INT < 21) {
                Drawable drawable = VectorDrawableCompat.create(imageIn.getResources(), R.drawable.about_in_black_24dp, imageIn.getContext().getTheme());
                imageIn.setImageDrawable(drawable);
            } else {
                imageIn.setImageResource(R.drawable.about_in_black_24dp);
            }

            Drawable wrappedDrawable = DrawableCompat.wrap(imageIn.getDrawable());
            wrappedDrawable = wrappedDrawable.mutate();

            if (!element.getSkipTint()) {
                if (element.getAutoApplyIconTint()) {
                    if (mEnableDarkMode) {
                        if (element.getIconNightTint() != null) {
                            DrawableCompat.setTint(wrappedDrawable, ContextCompat.getColor(mContext, element.getIconNightTint()));
                        } else {
                            DrawableCompat.setTint(wrappedDrawable, mIconColor);
                        }
                    } else {
                        if (element.getIconTint() != null) {
                            DrawableCompat.setTint(wrappedDrawable, ContextCompat.getColor(mContext, element.getIconTint()));
                        } else {
                            DrawableCompat.setTint(wrappedDrawable, mIconColor);
                        }
                    }

                } else if (element.getIconTint() != null) {
                    DrawableCompat.setTint(wrappedDrawable, ContextCompat.getColor(mContext, element.getIconTint()));
                } else if (mEnableDarkMode) {
                    DrawableCompat.setTint(wrappedDrawable, mIconColor);
                }

            }
            wrapper.addView(imageIn);
        }

        if (mIsRTL) {

            final int gravity = element.getGravity() != null ? element.getGravity() : Gravity.END;

//            wrapper.setGravity(gravity | Gravity.CENTER_VERTICAL);
            //noinspection ResourceType
//            textParams.gravity = gravity | Gravity.CENTER_VERTICAL;
            wrapper.addView(textView);
            if (element.getIconDrawable() != null) {
                wrapper.addView(iconView);
            }


        } else {
            final int gravity = element.getGravity() != null ? element.getGravity() : Gravity.START;
//            wrapper.setGravity(gravity | Gravity.CENTER_VERTICAL);
            //noinspection ResourceType
//            textParams.gravity = gravity | Gravity.CENTER_VERTICAL;
            if (element.getIconDrawable() != null) {
                wrapper.addView(iconView);
            }

            wrapper.addView(textView);
        }

        return wrapper;
    }


    private View getSeparator() {
        View separator = mInflater.inflate(R.layout.about_page_separator, null);
        separator.setBackgroundColor(mSeparatorColor);
        return separator;
    }
}
