package com.example.android.miwok;

public class Words {
    private String mDefaultTranslation;
    private String mMivokTranslation;
        private static final int NO_IMAGE_PROVIDED = -1;
    private int mImageResourceId = NO_IMAGE_PROVIDED;
    private int mAudioresId;
    public Words (String defaultTranslation, String mivokTranslation,int AudioresID){
     mDefaultTranslation=defaultTranslation;
     mMivokTranslation=mivokTranslation;
        mAudioresId=AudioresID;


    }

    public Words(String defaultTranslation, String mivokTranslation, int ImageResourceID,int AudioresID){
        mDefaultTranslation=defaultTranslation;
        mMivokTranslation=mivokTranslation;
        mImageResourceId=ImageResourceID;
        mAudioresId=AudioresID;
    }

    public String getmDefaultTranslation() {
        return mDefaultTranslation;
    }

    public String getmMivokTranslation() {
        return mMivokTranslation;
    }

    public int getmAudioresId() {
        return mAudioresId;
    }

    public void setmAudioresId(int mAudioresId) {
        this.mAudioresId = mAudioresId;
    }

    public int getmImageResourceId() {
        return mImageResourceId;
    }

    public boolean hasimage(){
          return mImageResourceId != NO_IMAGE_PROVIDED;
    }

}
