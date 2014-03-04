Dir['*.{bad,log}'].each{|f| File.delete f }

%w{member
notification
customer
supplier
goods_category
goods
special_price_goods
store
stock_taking_result
quotation_request_sheet
quotation_request_sheet_detail
customer_order_sheet
customer_order_sheet_detail
customer_order
billing
billing_detail
our_order_sheet
our_order_sheet_detail
our_order
payment
payment_detail}.each do |name|
	puts "-------\nLoad #{name}"
  system "sqlldr.bat #{name}"
end
