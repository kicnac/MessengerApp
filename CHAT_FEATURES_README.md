# Chat History and User Profile Features

## Overview
This implementation adds two new screens to the Messenger App:

1. **Chat History Screen** - Displays conversation history with alternating message bubbles
2. **User Profile Screen** - Shows user information with action buttons

## Features Implemented

### 1. Chat History Screen (ChatHistoryActivity)

**Location**: `app/src/main/java/com/example/messengerapp/ChatHistoryActivity.java`

**Features**:
- Receives chat ID and contact name via Intent from MainPage
- Displays conversation history in RecyclerView with alternating message layouts
- Messages from current user appear on the right with blue bubbles
- Messages from other users appear on the left with gray bubbles
- Each message shows: avatar (clickable), message text, and timestamp
- ActionBar shows contact name with back button
- Includes 10+ mock messages alternating between users
- Avatar clicks launch User Profile Screen

**Layout Files**:
- `activity_chat_history.xml` - Main layout with RecyclerView
- `item_message_sent.xml` - Layout for sent messages (right side, blue)
- `item_message_received.xml` - Layout for received messages (left side, gray)

**Supporting Classes**:
- `Message.java` - Data model for chat messages
- `MessageAdapter.java` - RecyclerView adapter with different view types

### 2. User Profile Screen (UserProfileActivity)

**Location**: `app/src/main/java/com/example/messengerapp/UserProfileActivity.java`

**Features**:
- Launched when clicking avatar in chat history
- Displays large profile image (center), name, and phone number
- Three action buttons horizontally:
  - **Mobile Call** (blue): Shows "Calling [name]" toast
  - **Message** (green): Shows "Messaging [name]" toast  
  - **Video Call** (purple): Shows "Video call [name]" toast
- ActionBar with back button

**Layout Files**:
- `activity_user_profile.xml` - Main layout with profile info and buttons

**Supporting Classes**:
- `User.java` - Data model for user information

### 3. Integration with MainPage

**Updates to MainPage.java**:
- Chat items are now clickable and launch ChatHistoryActivity
- Added chat ID to Chat data model
- Passes contact name and ID to chat history screen

## Drawable Resources Added

### Message Bubbles
- `message_bubble_sent.xml` - Blue bubble for sent messages
- `message_bubble_received.xml` - Gray bubble for received messages
- `circle_background.xml` - Circular background for avatars

### Button Backgrounds
- `button_call_background.xml` - Blue background for call button
- `button_message_background.xml` - Green background for message button
- `button_video_background.xml` - Purple background for video call button

### Icons
- `ic_phone.xml` - Phone icon for call button
- `ic_message.xml` - Message icon for message button
- `ic_video.xml` - Video call icon for video call button

## Navigation Flow

1. **MainPage** → Click on any chat item
2. **ChatHistoryActivity** → Shows conversation with mock messages
3. **ChatHistoryActivity** → Click on any avatar
4. **UserProfileActivity** → Shows user profile with action buttons

## Mock Data

- 10 chat items in MainPage with names "Name1" through "Name10"
- 11 mock messages in chat history (5 sent, 6 received)
- Generated phone numbers based on user ID hash
- Consistent timestamps for realistic conversation flow

## AndroidManifest.xml Updates

Added activity declarations for:
- `ChatHistoryActivity` (exported="false")
- `UserProfileActivity` (exported="false")

## Usage

1. Launch the app
2. Click on any chat item in the main page
3. View the chat history with alternating message bubbles
4. Click on any avatar to view the user profile
5. Try the action buttons to see toast messages
6. Use back button to navigate between screens 