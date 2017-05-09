function featureGraph(){
    var margin = {top: 40, right: 40, bottom: 40, left: 40};

    var svg = d3.select("svg"),
        width = +svg.attr("width"),
        height = +svg.attr("height");

    var color = d3.scaleOrdinal(d3.schemeCategory20);

    var simulation = d3.forceSimulation()
        .force("link", d3.forceLink().id(function(d){return d.id;}))
        .force("charge", d3.forceManyBody())
        .force("center", d3.forceCenter(width / 2, height / 2));

    d3.json("data.json", function(error, graph) {
        if (error) throw error;

        var link = svg.append("g")
            .attr("class", "links")
            .selectAll("line")
            .data(graph.links)
            .enter().append("line")
            .attr("stroke-width", function(d) { return Math.sqrt(d.value); })
            .style("marker-end", "url(#suit)");

        var gnodes = svg.selectAll("g.gnode")
            .data(graph.nodes)
            .enter().append("g")
            .classed("gnode", true)
            .call(d3.drag()
                .on("start", dragstarted)
                .on("drag", dragged)
                .on("end", dragended));

        var node = gnodes.append("circle")
            .attr("class", "nodes")
            .attr("r", 5)
            .style("fill", function(d) { return color(d.group); });


        gnodes.append("title")
            .text(function(d) { return d.id; });

        var labels = gnodes.append("text").text(function(d){return d.id;});
        labels.attr("transform", function(d){return "translate(" + (d.x) + "," + (d.y) + ")";})
            .style("font", "10px sans-serif")
            .style("stroke", "gray");


        simulation
            .nodes(graph.nodes)
            .on("tick", ticked);

        simulation.force("link")
            .links(graph.links);

        function ticked() {

            var radius = 10;

            link
                .attr("x1", function(d) { return d.source.x; })
                .attr("y1", function(d) { return d.source.y; })
                .attr("x2", function(d) { return d.target.x; })
                .attr("y2", function(d) { return d.target.y; });

            gnodes
                .attr("transform", function(d){
                    return "translate(" + [d.x, d.y] + ")";})
                .attr("cx", function(d){return d.x = Math.max(radius, Math.min(width - radius, d.x));})
                .attr("cy", function(d){return d.y = Math.max(radius, Math.min(height - radius, d.y));});

            labels.attr("transform", function(d) {return "translate" + (d.x) + (d.y)
                + ")";});

        }
    });

    function dragstarted(d) {
        if (!d3.event.active) simulation.alphaTarget(0.3).restart();
        d.fx = d.x;
        d.fy = d.y;
    }

    function dragged(d) {
        d.fx = d3.event.x;
        d.fy = d3.event.y;
    }

    function dragended(d) {
        if (!d3.event.active) simulation.alphaTarget(0);
        d.fx = null;
        d.fy = null;
    }
}
