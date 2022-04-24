package com.noob.web.model;
/**  
 * 定义计算器领域对象Calculator
 * 此处简易计算器的实现只是完成基本操作,没有对数据的运算类型作
 * 细化工作，只是作为JavaBean与JSP的结合使用的简单示例     
 */

public class Calculator {
	/**
	 *  领域对象的属性必须与相应的jsp文件中的定义的数据名称一致
	 *  使得其能自动封装相应的内容
	 */
	private double num1;
	private double num2;
	private char operator='+';// 默认加
	private double result;
	public Calculator() {
		super();
	}
	public Calculator(double num1, double num2, char operator, double result) {
		super();
		this.num1 = num1;
		this.num2 = num2;
		this.operator = operator;
		this.result = result;
	}
	public double getNum1() {
		return num1;
	}
	public void setNum1(double num1) {
		this.num1 = num1;
	}
	public double getNum2() {
		return num2;
	}
	public void setNum2(double num2) {
		this.num2 = num2;
	}
	public char getOperator() {
		return operator;
	}
	public void setOperator(char operator) {
		this.operator = operator;
	}
	public double getResult() {
		return result;
	}
	public void setResult(double result) {
		this.result = result;
	}
	@Override
	public String toString() {
		return "Calculator [num1=" + num1 + ", num2=" + num2 + ", operator=" + operator + ", result=" + result + "]";
	}
	public void cal() {
		switch(operator) {
			case '+':
				result = num1 + num2;
				break;
			case '-':
				result = num1 - num2;
				break;
			case '*':
				result = num1 * num2;
				break;
			case '/':
				if(num2==0) {
					throw new RuntimeException("被除数不能为0....");
				}
				result = num1 / num2;
				break;
			default:
				throw new RuntimeException("运算异常......");
		}
	}
}


