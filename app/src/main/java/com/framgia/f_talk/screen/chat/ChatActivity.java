package com.framgia.f_talk.screen.chat;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.framgia.f_talk.BR;
import com.framgia.f_talk.BaseActivity;
import com.framgia.f_talk.R;
import com.framgia.f_talk.data.model.Message;
import com.framgia.f_talk.data.model.MessageAccessType;
import com.framgia.f_talk.data.model.MessageContentType;
import com.framgia.f_talk.databinding.ActivityChatBinding;
import com.framgia.f_talk.util.Constant;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import javax.inject.Inject;

public class ChatActivity extends BaseActivity<ActivityChatBinding, ChatViewModel>
        implements ChatNavigator, ChatAdapter.ChatAdapterListener {
    private static final int PICK_IMAGE_REQUEST = 2210;
    @Inject
    ChatViewModel mChatViewModel;
    @Inject
    LinearLayoutManager mLayoutManager;
    @Inject
    ChatAdapter mChatAdapter;
    private String mUserId;
    private String mFriendId;
    private String mFriendName;
    private ActivityChatBinding mActivityChatBinding;
    private boolean mIsGroupMessageType;

    public static Intent newIntent(Context context) {
        return new Intent(context, ChatActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_chat;
    }

    @Override
    public ChatViewModel getViewModel() {
        return mChatViewModel;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityChatBinding = getViewDataBinding();
        mChatViewModel.setNavigator(this);
        mFriendId = getIntent().getStringExtra(Constant.EXTRA_RECEIVER_ID);
        mFriendName = getIntent().getStringExtra(Constant.EXTRA_RECEIVER_NAME);
        mUserId = FirebaseAuth.getInstance().getUid();
        mIsGroupMessageType = mFriendId.length() > mUserId.length();
        mChatAdapter.setListener(this);
        mChatAdapter.setUserId(mUserId);
        mLayoutManager.setStackFromEnd(true);
        mActivityChatBinding.recyclerMessage.setLayoutManager(mLayoutManager);
        mActivityChatBinding.recyclerMessage.setAdapter(mChatAdapter);
        mActivityChatBinding.textReceiverName.setText(mFriendName);
        mChatViewModel.setFriendName(mFriendName);
        mChatViewModel.fetchData(FirebaseDatabase.getInstance(), mUserId, mFriendId);
    }

    @Override
    public void getMessage() {
        String content = mActivityChatBinding.editTextMessage.getText().toString();
        Message message = new Message(mUserId, mIsGroupMessageType
                ? MessageAccessType.PUBLIC : MessageAccessType.PRIVATE,
                mFriendId, MessageContentType.TEXT, content);
        mChatViewModel.sendMessage(FirebaseDatabase.getInstance(), message);
    }

    @Override
    public void chooseImage() {
        Intent intent = new Intent();
        intent.setType(Constant.Image_TYPE);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,
                getString(R.string.title_select_picture)), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onSendMessageSuccess() {
        mActivityChatBinding.recyclerMessage.smoothScrollToPosition(mChatAdapter.getItemCount() - 1);
        mActivityChatBinding.editTextMessage.getText().clear();
    }

    @Override
    public void onSendMessageError() {

    }

    @Override
    public void onBack() {
        onBackPressed();
    }

    @Override
    public void scrollTo(int index) {
        mActivityChatBinding.recyclerMessage.scrollToPosition(index);
    }

    @Override
    public void onUploadImageSuccess(Uri uri) {
        Message message = new Message(mUserId, mIsGroupMessageType
                ? MessageAccessType.PUBLIC : MessageAccessType.PRIVATE,
                mFriendId, MessageContentType.IMAGE, uri.toString());
        mChatViewModel.sendMessage(FirebaseDatabase.getInstance(), message);
    }

    @Override
    public void onRetryClick() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
            Uri file = data.getData();
            mChatViewModel.uploadImageToStorage(FirebaseStorage.getInstance(), mUserId, file);
        }
    }
}
