## ContactInsert
[![Download](https://api.bintray.com/packages/townwang/ContactInsert/contactutils/images/download.svg)](https://bintray.com/townwang/ContactInsert/contactutils/_latestVersion)
[![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)

Operating library based on android address book.

## 使用方法（build.gradle）

```
dependencies {
 implementation 'com.townwang:contactutils:1.0.1'
}
```
## 实现功能

查询是否存在联系人

插入

删除

其他待定...

具体使用方法请看sample或源码（注释完善）

## 需要权限

```
    <!--读取联系人-->
    <uses-permission android:name="android.permission.READ_CONTACTS" />
    <!--写入联系人-->
    <uses-permission android:name="android.permission.WRITE_CONTACTS" />
```


## License
Copyright 2018 Townwang.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
