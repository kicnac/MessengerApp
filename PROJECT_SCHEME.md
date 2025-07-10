# 📱 Android Messenger App - Project Scheme

## 🏗️ **Project Overview**
A comprehensive Android messaging application with multiple features including chats, calls, groups, statuses, and user management.

---

## 📂 **Project Structure**

### **Core Activities (Java Classes)**

#### 🔐 **Authentication & Registration**
- **`MainActivity.java`** - Entry point with login functionality
  - Auto-navigates to MainPage (login bypassed for development)
  - Contains login validation logic (commented out)
  - Has user credential management with HashMap

- **`RegisterPage.java`** - User registration system
  - Collects user data (name, surname, birthdate, username)
  - Password validation with security requirements:
    - Minimum 8 characters
    - Must contain special characters (!,.%$#@&*())
    - Password confirmation matching

#### 🏠 **Main Application**
- **`MainPage.java`** - Central hub with chat functionality
  - **Features:**
    - Dynamic chat list display (10 test chats)
    - Expandable search functionality
    - Camera integration
    - More options menu with spinner
    - Navigation to all other sections
  - **UI Elements:**
    - Search bar with expand/collapse
    - Camera button
    - More options button
    - Navigation icons (Groups, Calls, Statuses, Add Chats)
    - Chat list with avatars, names, and last messages

#### 📞 **Calls Module**
- **`Calls.java`** - Call history and management
  - **Features:**
    - Call history with 15 test calls
    - Categorized display (Favorite/Recent)
    - Call type indicators (Incoming/Outgoing/Missed)
    - Contact avatars and timestamps
  - **Call Types:**
    - 🟢 Incoming calls (green icon)
    - 🔵 Outgoing calls (blue icon)
    - 🔴 Missed calls (red text + icon)

#### 👥 **Groups Module**
- **`Groups.java`** - Group chat management
  - **Features:**
    - Group list with 10 test groups
    - Favorite groups section
    - Member count display
    - Group avatars
  - **Categories:**
    - ⭐ Favorite groups
    - 📋 All groups

#### 📱 **Statuses Module**
- **`Statuses.java`** - Status/Story management
  - **Features:**
    - Status list with 15 test statuses
    - Recent vs. All statuses categorization
    - Contact avatars and timestamps
  - **Categories:**
    - ⏰ Recent statuses
    - 📋 All statuses

#### ➕ **Add New Chats**
- **`AddNewChats.java`** - Contact selection for new chats
  - **Features:**
    - Contact list with 16 contacts (3 special + 13 regular)
    - Special options: Group chat, New channel, Secret chat
    - Contact selection functionality
    - Last seen status display

---

## 🎨 **UI/UX Components**

### **Layout Files**
- `activity_main.xml` - Login screen layout
- `main_page_content.xml` - Main chat interface
- `calls_activity.xml` - Calls screen
- `groups_activity.xml` - Groups screen
- `states_activity.xml` - Statuses screen
- `add_new_chats_activity.xml` - Add contacts screen
- `register_content.xml` - Registration form

### **Reusable Components**
- `chat_string_content.xml` - Individual chat item
- `calls_item.xml` - Individual call item
- `group_item.xml` - Individual group item
- `status_item.xml` - Individual status item
- `headline.xml` - Section headers
- `add_btns_items.xml` - Action buttons

### **Visual Assets**
- **Icons:** calls.jpg, groups.jpg, states.jpg, settings.jpg, search.jpg
- **Call Icons:** incoming_call.png, outgoing_call.png, missed_call.png
- **Default Images:** efault_chat_img.png, photo.jpg
- **App Icons:** Multiple resolution launcher icons

---

## 🔄 **Navigation Flow**

```
MainActivity (Login) 
    ↓
MainPage (Chats Hub)
    ↓
├── Calls (Call History)
├── Groups (Group Management)
├── Statuses (Status/Stories)
└── AddNewChats (New Chat)
```

### **Navigation Features**
- **Cross-navigation** between all main sections
- **Consistent UI** with shared navigation elements
- **Back navigation** support
- **Intent-based** activity transitions

---

## 💾 **Data Models**

### **Chat Model**
```java
class Chat {
    String name;
    String lastMessage;
}
```

### **Call Model**
```java
class Call {
    String contactName;
    String dateOfCall;
    String imgName;
    boolean isFavorite;
    CallType type; // INCOMING, OUTGOING, MISSED
}
```

### **Group Model**
```java
class Group {
    String groupName;
    String membersCount;
    String imgName;
    boolean isFavorite;
}
```

### **Status Model**
```java
class Status {
    String contactName;
    String dateOfStatus;
    String imgName;
    boolean isRecent;
}
```

### **Contact Model**
```java
class Contact {
    String name;
    String status;
    int imageRes;
}
```

---

## ⚙️ **Technical Features**

### **Core Functionality**
- ✅ **Multi-Activity Architecture**
- ✅ **Dynamic UI Generation** (LayoutInflater)
- ✅ **Event Handling** (Click listeners)
- ✅ **Data Management** (Collections, Models)
- ✅ **Navigation System** (Intents)
- ✅ **Camera Integration** (MediaStore)
- ✅ **Spinner Controls** (Dropdown menus)

### **UI/UX Features**
- ✅ **Responsive Design** (LinearLayout, ScrollView)
- ✅ **Expandable Search** (Animated transitions)
- ✅ **Section Headers** (Categorized lists)
- ✅ **Avatar Support** (Image resources)
- ✅ **Color Coding** (Call types, status indicators)

### **Data Features**
- ✅ **Test Data Generation** (Random data for development)
- ✅ **Categorization** (Favorites, Recent, All)
- ✅ **Sorting** (By type, date, importance)
- ✅ **Filtering** (Search functionality)

---

## 🚀 **Development Status**

### **✅ Completed Features**
- Complete UI framework
- Navigation system
- Data models and structures
- Test data generation
- Basic functionality for all modules

### **🔄 In Progress/To Do**
- **Authentication System** (login logic commented out)
- **Real Data Integration** (currently using test data)
- **Database Implementation** (no persistent storage)
- **Network Communication** (no backend integration)
- **Message Functionality** (no actual messaging)
- **Real-time Features** (no live updates)

---

## 📱 **App Screenshots Summary**

1. **Login Screen** - User authentication (bypassed)
2. **Main Chat Hub** - Central messaging interface
3. **Calls Screen** - Call history with categories
4. **Groups Screen** - Group management
5. **Statuses Screen** - Status/story viewing
6. **Add Chats Screen** - Contact selection

---

## 🎯 **Project Strengths**

- **Well-structured architecture** with clear separation of concerns
- **Comprehensive UI** covering all major messaging app features
- **Consistent navigation** between all sections
- **Scalable data models** ready for backend integration
- **Professional UI components** with proper styling
- **Modular design** allowing easy feature additions

---

## 🔧 **Next Steps for Enhancement**

1. **Implement real authentication**
2. **Add database (SQLite/Room)**
3. **Integrate backend API**
4. **Add real-time messaging**
5. **Implement push notifications**
6. **Add media sharing capabilities**
7. **Enhance security features**
8. **Add user profile management**

---

*This project demonstrates a solid foundation for a modern Android messaging application with all essential UI components and navigation patterns implemented.* 