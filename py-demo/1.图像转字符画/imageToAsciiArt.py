from PIL import Image # PIL 是一个 Python 图像处理库

# 定义字符画所使用的字符集，字符的种类与数量可以根据字符画的效果反复调试
ascii_char = list("$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\|()1{}[]?-_+~<>i!lI;:,\"^`'. ")

# 定义字符画的宽度和高度
WIDTH = 60 
HEIGHT = 45

# 定义RGB值转字符的函数：将256灰度映射到指定的字符集上
def get_char(r, g, b, alpha=256):  # alpha透明度
   if alpha == 0:
       return ' '
   length = len(ascii_char)
   gray = int(0.2126 * r + 0.7152 * g + 0.0722 * b)  # 计算灰度
   unit = (256.0 + 1) / length
   return ascii_char[int(gray / unit)]  # 不同的灰度对应着不同的字符
   # 通过灰度来区分色块

# 主函数入口:接收输入图片和输出图片信息
if __name__ == '__main__':
   img = './test.png' # 图片所在位置(E:/temp/test.png)
   im = Image.open(img)
   im = im.resize((WIDTH, HEIGHT), Image.NEAREST)
   txt = ""
   for i in range(HEIGHT):
       for j in range(WIDTH):
           txt += get_char(*im.getpixel((j, i))) # 获得相应的字符
       txt += '\n'
   print(txt)  # 打印出字符画
   # 将字符画 写入文件中
   with open("./test.txt", 'w') as f:
       f.write(txt)