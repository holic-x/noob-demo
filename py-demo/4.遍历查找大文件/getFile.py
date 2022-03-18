import os
import re
 
 
def get_all(path, min_size, max_size):
    file_names = os.listdir(path)
    for file_name in file_names:
        file_name_path = os.path.join(path, file_name)
        if os.path.isdir(file_name_path):
            # print('文件夹', file_name_path)
            get_all(file_name_path, min_size, max_size)
        else:
            # print('文件', file_name_path)
            size = get_size(file_name_path)
            if min_size <= int(size) <= max_size:
                print('发现大文件', file_name_path)
                with open('big_file.txt', 'a+', encoding='utf-8') as f:
                    f.write(file_name_path)
                    f.write('\n')
 
 
def get_size(path):
    try:
        file_name = path[path.rfind('\\') + 1:]
        # print(file_name)
        ret_str = os.popen('dir %s' % path).read()
        # print(ret_str)
        search = re.search(r'(\d+,?\d+,?\d+,?\d+) %s' % file_name, ret_str)
        size = search.group(1) if search else '0'
        size = size.replace(',', '')
        return size
    except:
        pass
 
 
if __name__ == '__main__':
    # 查找 D:\\目录下所有 文件大小在 100M-1G 之间的文件(如果是win下需要以管理员权限开启cmd窗口运行脚本，否则可能出现访问拒绝的情况)
    get_all(path='D:\\', min_size=100 * 1024 * 1024, max_size=1024 * 1024 * 1024)