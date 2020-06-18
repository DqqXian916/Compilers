import java.util.Scanner;

/**
 * @Author:Star
 * @Date:Created in 12:10 2020/6/16
 * @Description:C语言实型常数识别程序设计
 */

public class test1 {

    // 表示各个状态
    final static int DIGIT = 1;
    final static int POINT = 2;
    final static int OTHER = 3;
    final static int POWER = 4;
    final static int PLUS = 5;
    final static int MINUS = 6;
    final static int EndState = -1;

    static int w = 0;	// 整数部分
    static int p = 0;	// 小数部分
    static int e = 1;	// 幂次是+ 还是 -
    static int d = 0;	// 暂存数据
    static int n = 0;	// 表示小数点后边有几个位
    static int currentState = 0;	// 当前状态，默认是0，后面执行的时候还要初始化
    static double result = 0.0;		// result用来存最后结果

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean isContinue = true;
        while (isContinue){
            System.out.println("请您输入要判断的字符串：");
            String str = input.next();//用户输入判断字符串
            if(isUnsignedNumber(str) == true) {
                System.out.println(str+" Yes"+" "+result);//确认为无符号数输出
            }else {
                System.out.println(str+" No");
            }
            System.out.println("是否继续判断：是(y)/否(n)");
            String flag = input.next();//用户输入是否继续
            if (flag.equals("n")){
                break;//退出，结束程序运行
            }else if(flag.equals("y")){
                continue;//继续，继续程序运行
            }else{
                System.out.println("输入错误......");//输入错误，打印提示信息并退出程序
                break;
            }
        }
    }

    // 解析字符，返回相应的状态，前面静态变量定义好了
    private static int analysisCharacter(char c) {
        switch (c) {
            case '.':
                return POINT;//返回POINT状态值2
            case 'E':
            case 'e':
                return POWER;//返回POWER状态值4
            case '+':
                return PLUS;//返回PLUS状态值5
            case '-':
                return MINUS;//返回MINUS状态值6
            default:
                if (Character.isDigit(c)) {//判断c是否是数字
                    d = c - '0';//将c(字符数字)->d(int值)
                    return DIGIT;//返回DIGIT状态值1
                }
                break;
        }
        return OTHER;//返回OTHER状态值3
    }

    //判断是不是无符号整数，返回相应的状态
    private static boolean isUnsignedNumber(String s) {
        // 在这里全部都要重新初始化
        currentState = 0;	// 当前状态初始化0
        n = 0;			    // 小数点后位数初始化0
        w = 0;			    // 整数部分初始化0
        p = 0;			    // 小数部分初始化0
        e = 1;			    // 默认是＋
        for(int i=0;i<s.length();i++) {//遍历用户输入字符串
            int ch = analysisCharacter(s.charAt(i));//分析字符串的每一个字符
            currentToNext(ch);//将当前字符的状态值传入currentToNext()函数
            if(currentState == EndState) {
                return false;//如果当前状态为结束状态，返回false，判断为非无符号整数
            }
        }
        switch(currentState) {//1,2,6代表终止状态
            case 1:
            case 2:
            case 6:
                result = w * Math.pow(10,e*p-n);//使用科学计数法表示
                return true;
        }
        return false;
    }

    // 函数功能：状态转化图的代码转换
    // 从当前状态到下一个状态的执行，参数是一个字符状态值，currentState是静态成员变量不需要传参
    private static void currentToNext(int ch) {
        switch (currentState) {//根据当前状态选择后续操作，执行到下一个操作
            case 0://初始状态
                switch (ch) {
                    case DIGIT:
                        w = w * 10 + d;//整数部分重新赋值为整数*10+暂存数据
                        currentState = 1;//当前状态转化为1状态
                        break;
                    case POINT:
                        currentState = 3;//当前状态转化为3状态
                        break;
                    default:
                        currentState = EndState;////当前状态转化为结束状态
                        break;
                }
                break;
            case 1:
                switch (ch) {
                    case DIGIT:
                        w = w * 10 + d;//整数部分重新赋值为整数*10+暂存数据
                        currentState = 1;//当前状态转化为1状态
                        break;
                    case POINT:
                        currentState = 2;//当前状态转化为2状态
                        break;
                    case POWER:
                        currentState = 4;//当前状态转化为4状态
                        break;
                    default:
                        currentState = EndState;//当前状态转化为结束状态
                        break;
                }
                break;
            case 2:
                switch (ch) {
                    case DIGIT:
                        w = w * 10 + d;//整数部分重新赋值为整数*10+暂存数据
                        ++n;//小数后边位数+1
                        currentState = 2;//当前状态转化为2状态
                        break;
                    case POWER:
                        currentState = 4;//当前状态转化为4状态
                        break;
                    default:
                        currentState = EndState;//当前状态转化为结束状态
                        break;
                }
                break;
            case 3:
                switch (ch) {
                    case DIGIT:
                        w = w * 10 + d;//整数部分重新赋值为整数*10+暂存数据
                        ++n;//小数后边位数+1
                        currentState = 2;//当前状态转化为2状态
                        break;
                    default:
                        currentState = EndState;//当前状态转化为结束状态
                        break;
                }
                break;
            case 4:
                switch (ch) {
                    case DIGIT:
                        p = p * 10 + d;//小数部分重新赋值为小数*10+暂存数据
                        currentState = 6;//当前状态转化为6状态
                        break;
                    case PLUS:
                        currentState = 5;//当前状态转化为5状态
                        break;
                    case MINUS:
                        e = -1;//幂次修改为 -
                        currentState = 5;//当前状态转化为5状态
                        break;
                    default:
                        currentState = EndState;//当前状态转化为结束状态
                        break;
                }
                break;
            case 5:
                switch (ch) {
                    case DIGIT:
                        p = p * 10 + d;//小数部分重新赋值为小数*10+暂存数据
                        currentState = 6;//当前状态转化为6状态
                        break;
                    default:
                        currentState = EndState;//当前状态转化为结束状态
                        break;
                }
                break;
            case 6:
                switch (ch) {
                    case DIGIT:
                        p = p * 10 + d;//小数部分重新赋值为小数*10+暂存数据
                        currentState = 6;//当前状态转化为6状态
                        break;
                    default:
                        currentState = EndState;//当前状态转化为结束状态
                        break;
                }
                break;
        }
    }
}
